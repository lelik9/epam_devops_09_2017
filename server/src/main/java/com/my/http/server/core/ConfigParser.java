package com.my.http.server.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigParser {
    private static ConfigParser ourInstance = new ConfigParser();
    @Getter
    private ServerConfig serverConfig;

    public static ConfigParser getInstance() {
        return ourInstance;
    }

    public AppConfig getAppConfig(InputStream inputStream) {
        return (AppConfig) parse(inputStream, AppConfig.class);
    }

    private ConfigParser() {
//        InputStream inputStream = readFile("/Users/lelik9/config.json");
        File inputStream = readFile("/Users/lelik9/config.json");

        this.serverConfig = (ServerConfig) parse(inputStream, ServerConfig.class);
    }


    private File readFile(String fileName) {
        InputStream targetStream = null;
        File file = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
//            File file = new File(classLoader.getResource(fileName).getFile());
            file = new File(fileName);
            System.out.println(file);
            targetStream = new FileInputStream(file);

        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    @SneakyThrows
    private Object parse(File file, Class configClass) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, configClass);
    }

    @SneakyThrows
    private Object parse(InputStream inputStream, Class configClass) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, configClass);
    }

}

@Getter
class ServerConfig {
    private HashMap<String, Object> server;
    private ArrayList<HashMap<String, String>> endpoints;
}

@Getter
class AppConfig {
    private ArrayList<HashMap<String, String>> urls;
}
