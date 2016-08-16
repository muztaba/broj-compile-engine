package com.muztaba.service.producer;

import com.muztaba.model.User;
import com.muztaba.service.UserService;
import com.muztaba.service.producer.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by seal on 8/13/2016.
 */
public class MyTaskImpl implements MyTask {

    private static final Logger logger = LoggerFactory.getLogger(MyTaskImpl.class);

    @Autowired
    UserService userService;

    private long count;

    @Scheduled(fixedDelay = 100)
    public void scheduledTask() {
        User user = new User();
        user.setName("seal-" + String.valueOf(count++));
        user.setTime(new Date());
//        logger.info("{}", user);
        userService.post(user);
    }

    @Scheduled(fixedDelay = 4000)
    public void scheduledRetrieve() {
        List<User> userList = userService.getUserList();
        logger.info("List size {}", userList.size());
        logger.info("{}",userList);
        List<Long> idList = getUserId(userList);
        userService.usersUpdate(idList);
    }

    private List<Long> getUserId(List<User> list) {
        List<Long> longList = new ArrayList<Long>(list.size());
        for (User i : list) {
            longList.add(i.getId());
        }
        return longList;
    }
}
