package com.muztaba.config;

import com.muztaba.entity.Verdict;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by seal on 8/13/2016.
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.muztaba")
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

    @Bean
    public ExecutorCompletionService<Verdict> getBean() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        return new ExecutorCompletionService<>(executorService);
    }
}
