package com.epam.Philippov.http.server.engine.view;

import com.epam.Philippov.http.server.engine.Request;
import com.epam.Philippov.http.server.engine.ResponseInterface;

public interface ViewInterface {
    ResponseInterface get(Request request);
    ResponseInterface post(Request request);
}
