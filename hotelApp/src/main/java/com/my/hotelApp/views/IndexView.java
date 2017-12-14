package com.my.hotelApp.views;


import com.my.http.framework.AuthenticationAnnotation;
import com.my.http.framework.Request;
import com.my.http.framework.response.FileResponse;
import com.my.http.framework.response.Response;
import com.my.http.framework.session.Session;
import com.my.http.framework.view.View;

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
