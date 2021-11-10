package com.antra.videomanager.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static final Logger LOGGER = LogManager.getLogger(PropertyUtil.class.getName());

    public static Properties fetchProperties(String propertyName){
        Properties properties = new Properties();
        try( InputStream in = new ClassPathResource(propertyName).getInputStream()) {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return properties;
    }
}
