package com.epam.Philippov.http.server.views;

import com.epam.Philippov.http.server.Handler;

public class URLHandler extends Handler {
    {
        urlPatterns.put("/index", IndexView.class);
        urlPatterns.put("/static", StaticView.class);
    }
}
