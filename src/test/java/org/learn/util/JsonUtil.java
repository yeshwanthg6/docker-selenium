package org.learn.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {
    public static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path,Class<T>  classType){
        log.info("File is being fetched from {}",path);
        try(InputStream stream = ResourceLoader.getResource(path))
        {
            return mapper.readValue(stream, classType);
        }catch (IOException e){
            log.info("Unable to read file from {}",path,e);

        }
        return null;
    }
}
