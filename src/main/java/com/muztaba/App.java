package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.model.User;
import com.muztaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

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
