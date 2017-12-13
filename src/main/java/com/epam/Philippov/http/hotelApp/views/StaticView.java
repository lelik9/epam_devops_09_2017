package com.epam.Philippov.http.hotelApp.views;

import com.epam.Philippov.http.framework.response.FileResponse;
import com.epam.Philippov.http.framework.Request;
import com.epam.Philippov.http.framework.response.Response;

import com.epam.Philippov.http.framework.view.View;
import lombok.SneakyThrows;

public class StaticView extends View {

    @Override
    @SneakyThrows
    public Response get(Request request) {
        return new FileResponse(staticPath+request.getUrl()+"/",request.getQuery());
    }

}
