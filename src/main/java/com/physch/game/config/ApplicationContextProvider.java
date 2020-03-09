package com.physch.game.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext applicationContext() {
        return applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext= context;

    }
}
