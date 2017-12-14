package com.my.http.framework.middleware;


import com.my.http.framework.AuthenticationAnnotation;
import com.my.http.framework.Request;
import com.my.http.framework.view.View;
import java.lang.reflect.Method;
import lombok.SneakyThrows;


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
