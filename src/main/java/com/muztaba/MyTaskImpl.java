package com.muztaba;

import com.muztaba.model.User;
import com.muztaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by seal on 8/13/2016.
 */
public class MyTaskImpl implements MyTask{

    @Autowired
    UserService userService;

    @Scheduled(fixedDelay = 2000)
    public void scheduledTask() {
        User user = new User();
        user.setName("Pranjol");
        System.out.println("**");
        userService.post(user);
    }
}
