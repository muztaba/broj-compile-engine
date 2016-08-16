package com.muztaba.config;

import com.muztaba.service.producer.MyTask;
import com.muztaba.service.producer.MyTaskImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by seal on 8/13/2016.
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.muztaba")
public class AppConfig {

    @Bean
    public MyTask getbean() {
        return new MyTaskImpl();
    }
}
