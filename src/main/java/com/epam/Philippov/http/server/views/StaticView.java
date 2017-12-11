package com.epam.Philippov.http.server.views;

import com.epam.Philippov.http.server.engine.FileResponse;
import com.epam.Philippov.http.server.engine.Request;
import com.epam.Philippov.http.server.engine.Response;

import com.epam.Philippov.http.server.engine.framework.view.View;
import lombok.SneakyThrows;

public class StaticView extends View {

    @Override
    @SneakyThrows
    public Response get(Request request) {
        return new FileResponse(staticPath+request.getUrl()+"/",request.getQuery());
    }

}
