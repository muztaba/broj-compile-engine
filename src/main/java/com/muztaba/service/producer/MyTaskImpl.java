package com.muztaba.service.producer;

import com.muztaba.model.Problem;
import com.muztaba.model.Submission;
import com.muztaba.service.ProblemService;
import com.muztaba.service.SubmissionService;
import com.muztaba.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * Created by seal on 8/13/2016.
 */
@Component
public class MyTaskImpl implements MyTask {

    private static final Logger logger = LoggerFactory.getLogger(MyTaskImpl.class);

    @Autowired
    ProblemService problemService;

    @Autowired
    SubmissionService submissionService;

    private long count;

    private static final Random r = new Random();
    private static final String SRC_PATH = "/home/seal/test/A.cpp";

    @Scheduled(fixedDelay = 100)
    public void scheduledTask() {
        Problem problem = problemService.load(r.nextInt(5) + 1);
        Submission submission = new Submission();
        submission.setProblem(problem);
        submission.setUserName("seal-" + String.valueOf(++count));
        submission.setTime(new Date());
        submission.setSrcFile(FileUtil.readFileAsByte(SRC_PATH));
        submissionService.post(submission);
    }
}
