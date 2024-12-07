package org.learn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static final Logger log = LoggerFactory.getLogger(ConfigReader.class);
    public static final String DEFAULT_PROPERTIES = "Config/default.properties";
    public static Properties properties;

    public static void initializeProperties(){
        properties = loadProperties();

        for(String key:properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key,System.getProperty(key));
            }
        }

        log.info("TEST PROPERTIES");
        log.info("_______________");
        for(String key:properties.stringPropertyNames()){
            log.info("{}:{}",key,properties.getProperty(key));
        }
        log.info("_______________");
    }

    public static String getKey(String key){
        return properties.getProperty(key);
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();
        try(InputStream stream=ResourceLoader.getResource(DEFAULT_PROPERTIES))
        {
            properties.load(stream);
        }catch (IOException e){
            log.info("Unable to fetch file from {}",DEFAULT_PROPERTIES,e);
        }
        return properties;
    }
}
