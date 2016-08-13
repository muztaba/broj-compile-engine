package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.model.User;
import com.muztaba.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) context.getBean("userService");

        User user = new User();
        user.setName("Pranjol");

        User user1 = new User();
        user1.setName("Ruisul");

        userService.post(user);
        userService.post(user1);
    }
}
