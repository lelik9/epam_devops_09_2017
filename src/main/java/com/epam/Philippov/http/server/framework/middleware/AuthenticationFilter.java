package com.epam.Philippov.http.server.framework.middleware;

import com.epam.Philippov.http.server.framework.AuthenticationAnnotation;
import com.epam.Philippov.http.server.framework.Request;

import com.epam.Philippov.http.server.framework.view.View;
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
