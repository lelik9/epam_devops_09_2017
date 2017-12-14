package com.my.http.server.core;

import java.util.HashMap;

import com.my.http.framework.errors.Http404;
import com.my.http.framework.view.View;
import org.apache.logging.log4j.core.util.ArrayUtils;

public class HandlerFactory {
    private HashMap<String, Handler> handlersList = new HashMap<>();
    private JarReader jarReader;

    public HandlerFactory() {
        ServerConfig serverConfig = ConfigParser.getInstance().getServerConfig();

        generateEndPoints(serverConfig);
    }

    public Handler getHandler(String url) {
        String endPoint = url;
        String[] pathArr;
        System.out.println(handlersList);
        System.out.println(endPoint);
        while (!handlersList.containsKey(endPoint)) {
            pathArr = endPoint.split("/");
            if(pathArr.length == 1) {
                throw new Http404();
            }
            pathArr = ArrayUtils.remove(pathArr, pathArr.length - 1);

            endPoint = String.join("/", pathArr) + "/";
        }
        return handlersList.get(endPoint);
    }

    private void generateEndPoints(ServerConfig serverConfig) {
        for (HashMap<String,String> endpoint : serverConfig.getEndpoints()) {
            jarReader = new JarReader(endpoint.get("appName"));
            AppConfig appConfig = ConfigParser.getInstance().getAppConfig(jarReader.readAppConfig());

            handlersList.put(endpoint.get("endpoint"), generateHandler(appConfig));
        }
    }

    private Handler generateHandler(AppConfig appConfig){
        Handler handler = new Handler("hotelSite");
        HashMap<String, View> urlPatterns = new HashMap<>();
        try {
            for (HashMap<String,String> urlPattern : appConfig.getUrls()) {
                urlPatterns.put(urlPattern.get("url"), (View) jarReader.loadClass(urlPattern.get("viewClass")).newInstance());
            }

            handler.registeredURL(urlPatterns);
        } catch (IllegalAccessException | InstantiationException e) {
            urlPatterns.put("/", null);

            e.printStackTrace();
        }
        return handler;
    }


}
