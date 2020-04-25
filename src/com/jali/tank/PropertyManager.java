package com.jali.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lijiang
 * @create 2020-04-25 9:00
 */
public class PropertyManager {

    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String get(String key){
        return properties.getProperty(key);
    }

  }
