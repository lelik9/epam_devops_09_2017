package com.epam.Philippov.http.server.views;

import com.epam.Philippov.http.server.engine.FileResponse;
import com.epam.Philippov.http.server.engine.Request;
import com.epam.Philippov.http.server.engine.ResponseInterface;
import com.epam.Philippov.http.server.engine.view.View;
import lombok.SneakyThrows;

public class StaticView extends View {

    @Override
    @SneakyThrows
    public ResponseInterface get(Request request) {
        return new FileResponse(staticPath,request.getQuery());
    }

}
