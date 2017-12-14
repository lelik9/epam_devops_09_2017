package com.my.http.server.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.my.http.framework.errors.Http404;
import com.my.http.framework.view.View;
import lombok.SneakyThrows;
import org.apache.logging.log4j.core.util.ArrayUtils;

public class HandlerFactory {
    private HashMap<String, Handler> handlersList = new HashMap<>();

    public HandlerFactory() {
        readJar();
        handlersList.put("/hotel/", generatrHandler());
    }

    public Handler getHandler(String url) {
        String endPoint = url;
        String[] pathArr;

        while (!handlersList.containsKey(endPoint)) {
            pathArr = endPoint.split("/");
//            System.out.println(endPoint);
            if(pathArr.length == 1) {
                throw new Http404();
            }
            pathArr = ArrayUtils.remove(pathArr, pathArr.length - 1);

            endPoint = String.join("/", pathArr) + "/";
        }
        return handlersList.get(endPoint);
    }

    private Handler generatrHandler(){
        Handler handler = new Handler("hotelSite");
        HashMap<String, View> urlPatterns = new HashMap<>();
        try {
            urlPatterns.put("/hotel/", (View) Class.forName("com.my.hotelApp.views.IndexView").newInstance());
            urlPatterns.put("/hotel/Гостиница «Волхов» – официальный сайт_files", (View) Class.forName("com.my.hotelApp.views.StaticView").newInstance());
            handler.registeredURL(urlPatterns);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            urlPatterns.put("/", null);

            e.printStackTrace();
        }
        return handler;
    }

    @SneakyThrows
    public void readJar(){
        String pathToJar = "hotelApp.jar";
        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
//            System.out.println(je.getName());
//            if(je.isDirectory() || !je.getName().endsWith(".class") || !je.getName().endsWith("config.json")){
//                continue;
//            }
            if(je.getName().endsWith(".class")) {
                String className = je.getName().substring(0, je.getName().length() - 6);
                System.out.println(className);
                className = className.replace('/', '.');
                Class c = cl.loadClass(className);
                System.out.println(c);
            } else if(je.getName().endsWith("config.json")) {
//                AppConfig appConfig = ConfigParser.getInstance().getAppConfig(je.getName());
//                System.out.println(appConfig.getUrls());
            }
        }
    }
}
