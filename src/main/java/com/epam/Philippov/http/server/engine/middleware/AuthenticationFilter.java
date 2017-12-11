package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.AuthenticationAnnotation;
import com.epam.Philippov.http.server.engine.Request;

import com.epam.Philippov.http.server.engine.framework.view.View;
import lombok.SneakyThrows;
import java.lang.reflect.Method;


public class AuthenticationFilter implements PreFilter {
    @Override
    @SneakyThrows
    public Request call(Request request) {
        View view = request.getViewClass();
        Method method = view.getClass().getMethod(request.getMethod(), Request.class);

        if(method.isAnnotationPresent(AuthenticationAnnotation.class)) {
            System.out.println("Auth!");
        }

        return request;
    }
}
