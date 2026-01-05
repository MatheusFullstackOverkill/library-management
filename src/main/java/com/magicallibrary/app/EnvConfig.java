package com.magicallibrary.app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class EnvConfig {
    private Properties props = new Properties();

    public EnvConfig() {
        Path envFIle = Paths.get(".env");
        try {
            var inputStream = Files.newInputStream(envFIle); 
            props.load(inputStream);
        } catch(Exception e) {
            e.printStackTrace();
        };
    }

    public String getEnv(String name) {
        return props.get(name).toString();
    }
}