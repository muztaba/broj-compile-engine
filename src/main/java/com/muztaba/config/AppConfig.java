package com.muztaba.config;

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
}
