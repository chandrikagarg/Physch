package com.physch.game.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public static ApplicationContextProvider contextProvider()
    {
        return new ApplicationContextProvider();
    }
}
