package com.epam.Philippov.http.server.views;

import com.epam.Philippov.http.server.engine.*;
import com.epam.Philippov.http.server.engine.view.View;

public class IndexView extends View {
    @Override
    @AuthenticationAnnotation
    public Response get(Request query) {
        return new FileResponse(staticPath, "Гостиница «Волхов» – официальный сайт.html");
    }
}
