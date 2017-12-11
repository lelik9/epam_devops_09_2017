package com.epam.Philippov.http.server.hotelApp.views;

import com.epam.Philippov.http.server.engine.framework.response.FileResponse;
import com.epam.Philippov.http.server.engine.framework.Request;
import com.epam.Philippov.http.server.engine.framework.response.Response;

import com.epam.Philippov.http.server.engine.framework.view.View;
import lombok.SneakyThrows;

public class StaticView extends View {

    @Override
    @SneakyThrows
    public Response get(Request request) {
        return new FileResponse(staticPath+request.getUrl()+"/",request.getQuery());
    }

}
