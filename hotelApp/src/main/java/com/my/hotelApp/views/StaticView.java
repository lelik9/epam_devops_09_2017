package com.my.hotelApp.views;

import com.my.http.framework.Request;
import com.my.http.framework.response.FileResponse;
import com.my.http.framework.response.Response;
import com.my.http.framework.view.View;
import lombok.SneakyThrows;

public class StaticView extends View {

    @Override
    @SneakyThrows
    public Response get(Request request) {
        return new FileResponse(staticPath+request.getUrl()+"/",request.getQuery());
    }

}
