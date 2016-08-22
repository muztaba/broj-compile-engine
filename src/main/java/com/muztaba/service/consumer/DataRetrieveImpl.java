package com.muztaba.service.consumer;

import com.muztaba.entity.Submission;
import com.muztaba.service.entity.SubmissionService;
import com.muztaba.service.processor.QueueService;
import com.muztaba.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by seal on 8/17/2016.
 */
@Component
public class DataRetrieveImpl implements DataRetrieve{

    private static final Logger logger = LoggerFactory.getLogger(DataRetrieveImpl.class);

    private final BlockingDeque<Submission> q = new LinkedBlockingDeque<>();

    @Autowired
    SubmissionService submissionService;

    @Autowired
    QueueService<Submission> queue;

    private List<Long> getUserId(List<Submission> list) {
        List<Long> longList = new ArrayList<>(list.size());
        for (Submission i : list) {
            longList.add(i.getId());
        }
        return longList;
    }

    private static final String DIR_PATH = "/home/seal/test1/";

    private void createDir(List<Submission> submissions) {
        for (Submission i : submissions) {
            String path = DIR_PATH + i.getId();
            FileUtil.createDirectory(path);
            logger.info("Directory create at {}", path);
            FileUtil.writeByteToFile(i.getSrcFile(), path + "/A.cpp");
            FileUtil.writeByteToFile(i.getProblem().getInputFile(), path + "/input.in");
            FileUtil.writeByteToFile(i.getProblem().getResultFile(), path + "/output.out");
        }
    }

    private void print(List<Submission> submissions) {
        for (Submission submission : submissions) {
            System.out.println(submission);
        }
    }

/*    @Scheduled(fixedDelay = 4000)
    public void scheduledRetrieve() {
        List<Submission> submissions = submissionService.getSubmissionList();
        createDir(submissions);
        logger.info("List size {}", submissions.size());
//        logger.info("{}",submissions);
//        print(submissions);
        List<Long> idList = getUserId(submissions);
        submissionService.submissionUpdate(idList);
    }*/

    @Scheduled(fixedDelay = 4000)
    public void scheduledRetrieve() {
        int required = 2;
        if (required != 0) {
            List<Submission> submissions = submissionService.getSubmissionList(required);
//            logger.info("List size {}", submissions.size());
            queue.add(submissions);
//        logger.info("{}",submissions);
//        print(submissions);
            List<Long> idList = getUserId(submissions);
            submissionService.submissionUpdate(idList);
        }
    }


    @Scheduled(fixedDelay = 2000)
    public void test() {
        /*List<Submission> submissions = queue.getList(2);
        createDir(submissions);*/
    }
}
