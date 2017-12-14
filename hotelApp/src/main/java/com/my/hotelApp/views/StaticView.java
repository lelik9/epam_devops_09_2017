package com.my.hotelApp.views;
import lombok.SneakyThrows;

public class StaticView extends View {

    @Override
    @SneakyThrows
    public Response get(Request request) {
        return new FileResponse(staticPath+request.getUrl()+"/",request.getQuery());
    }

}
