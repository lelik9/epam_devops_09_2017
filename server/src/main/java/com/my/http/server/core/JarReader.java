package com.my.http.server.core;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarReader {
    private Enumeration<JarEntry> e;
    private URLClassLoader cl;
    private InputStream in = null;


    @SneakyThrows
    public JarReader(String appName) {
        String pathToJar = appName + ".jar";
        JarFile jarFile = new JarFile(pathToJar);
        e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
        cl = URLClassLoader.newInstance(urls);
    }

    @SneakyThrows
    public void loadClasses() {
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();

            if (je.getName().endsWith(".class")) {
                String className = je.getName().substring(0, je.getName().length() - 6);
//                System.out.println(className);
                className = className.replace('/', '.');
                Class c = cl.loadClass(className);
//                System.out.println(c);
            } else  if (je.getName().endsWith("config.json")) {
                in = cl.getResourceAsStream(je.getName());
            }
        }
    }

    @SneakyThrows
    public Class loadClass(String className) {
        return cl.loadClass(className);
    }

    @SneakyThrows
    public InputStream readAppConfig() {

//        while (e.hasMoreElements()) {
//            JarEntry je = e.nextElement();
//            System.out.println(je);
//            if (je.getName().endsWith("config.json")) {
//                in = cl.getResourceAsStream(je.getName());
//            }
//        }
        return cl.getResourceAsStream("com/my/hotelApp/config.json");
    }

}
