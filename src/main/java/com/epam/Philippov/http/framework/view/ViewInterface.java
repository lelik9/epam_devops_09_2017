package com.epam.Philippov.http.framework.view;

import com.epam.Philippov.http.framework.Request;
import com.epam.Philippov.http.framework.response.Response;

public interface ViewInterface {
    Response get(Request request);
    Response post(Request request);
}
