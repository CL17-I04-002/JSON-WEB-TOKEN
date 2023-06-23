package com.proyecto.jwt.util;

import org.aspectj.apache.bcel.util.ClassPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class FilesHandler {
    public FilesHandler(){

    }
    public static String loadKey(String name, String key) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = FilesHandler.class.getClassLoader().getResourceAsStream(name);
        properties.load(inputStream);

        String keyFound = properties.getProperty(key);
        return keyFound;
    }
}