package com.my.http.server.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigParser {
    private static ConfigParser ourInstance = new ConfigParser();
    @Getter
    private ServerConfig serverConfig;

    public static ConfigParser getInstance() {
        return ourInstance;
    }

    public AppConfig getAppConfig(String fileName) {
        return (AppConfig) parseServerConfig(fileName, AppConfig.class);
    }

    private ConfigParser() {
        this.serverConfig = (ServerConfig) parseServerConfig("config.json", ServerConfig.class);
    }

    private Object parseServerConfig(String fileName, Class configClass) {
        ObjectMapper mapper = new ObjectMapper();
        Object config = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            config = mapper.readValue(file, configClass);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
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
