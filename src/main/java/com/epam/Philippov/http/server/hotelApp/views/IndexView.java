package com.epam.Philippov.http.server.hotelApp.views;

import com.epam.Philippov.http.server.engine.core.Session;
import com.epam.Philippov.http.server.engine.framework.AuthenticationAnnotation;
import com.epam.Philippov.http.server.engine.framework.Request;
import com.epam.Philippov.http.server.engine.framework.response.FileResponse;
import com.epam.Philippov.http.server.engine.framework.response.Response;
import com.epam.Philippov.http.server.engine.framework.view.View;


public class IndexView extends View {
    @Override
    @AuthenticationAnnotation
    public Response get(Request query) {
        Session session = null;
        try {
            session = query.getSession();

            System.out.println((String) session.getValue("UserName"));
        } catch (Exception ex) {
            System.err.println("Session not found " + query.getCoockiesAsString());
        }
        return new FileResponse(staticPath, "Гостиница «Волхов» – официальный сайт.html");
    }
}
