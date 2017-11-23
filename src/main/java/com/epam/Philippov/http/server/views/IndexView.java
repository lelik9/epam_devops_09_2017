package com.epam.Philippov.http.server.views;

import com.epam.Philippov.http.server.engine.AuthenticationAnnotation;
import com.epam.Philippov.http.server.engine.FileResponse;
import com.epam.Philippov.http.server.engine.Request;
import com.epam.Philippov.http.server.engine.ResponseInterface;
import com.epam.Philippov.http.server.engine.view.View;

public class IndexView extends View {
    @Override
    @AuthenticationAnnotation
    public ResponseInterface get(Request query) {
        return new FileResponse(staticPath, "Гостиница «Волхов» – официальный сайт.html");
    }
}
