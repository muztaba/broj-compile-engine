package com.muztaba.service.consumer;

import com.muztaba.model.Submission;
import com.muztaba.model.User;
import com.muztaba.service.SubmissionService;
import com.muztaba.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seal on 8/17/2016.
 */
@Component
public class DataRetrieveImpl implements DataRetrieve{

    private static final Logger logger = LoggerFactory.getLogger(DataRetrieveImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    SubmissionService submissionService;
/*
    @Scheduled(fixedDelay = 4000)
    public void scheduledRetrieve() {
        List<User> userList = userService.getUserList();
        logger.info("List size {}", userList.size());
        logger.info("{}",userList);
        List<Long> idList = getUserId(userList);
        userService.usersUpdate(idList);
    }
    */

/*    private List<Long> getUserId(List<User> list) {
        List<Long> longList = new ArrayList<>(list.size());
        for (User i : list) {
            longList.add(i.getId());
        }
        return longList;
    }*/

    private List<Long> getUserId(List<Submission> list) {
        List<Long> longList = new ArrayList<>(list.size());
        for (Submission i : list) {
            longList.add(i.getId());
        }
        return longList;
    }

    @Scheduled(fixedDelay = 4000)
    public void scheduledRetrieve() {
        List<Submission> submissions = submissionService.getSubmissionList();
        logger.info("List size {}", submissions.size());
        logger.info("{}",submissions);
        List<Long> idList = getUserId(submissions);
        submissionService.submissionUpdate(idList);
    }
}
