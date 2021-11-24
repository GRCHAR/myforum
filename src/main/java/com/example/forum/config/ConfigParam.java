package com.example.forum.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author genghaoran
 */
@Component
@ConfigurationProperties(prefix = "user")
public class ConfigParam {

    private int userImagePath;

    public ConfigParam(){

    }

    public int getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(int userImagePath) {
        this.userImagePath = userImagePath;
    }
}
