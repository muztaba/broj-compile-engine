package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.service.producer.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    @Autowired
    static MyTask task;
    public static void main( String[] args ) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
