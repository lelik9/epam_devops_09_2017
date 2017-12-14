package com.my.hotelApp.views;

import com.epam.Philippov.http.framework.AuthenticationAnnotation;
import com.epam.Philippov.http.framework.Request;
import com.epam.Philippov.http.framework.response.FileResponse;
import com.epam.Philippov.http.framework.response.Response;
import com.epam.Philippov.http.framework.view.View;
import com.epam.Philippov.http.server.core.Session;


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
