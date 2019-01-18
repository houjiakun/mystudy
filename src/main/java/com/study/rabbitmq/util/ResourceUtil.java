package com.study.rabbitmq.util;

import java.util.ResourceBundle;

/**
 * 配置文件读取工具类
 */
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("application");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

}
