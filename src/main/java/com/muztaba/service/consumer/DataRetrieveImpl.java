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
import java.util.stream.Collectors;

/**
 * Created by seal on 8/17/2016.
 */
@Component
public class DataRetrieveImpl implements DataRetrieve{

    private static final Logger logger = LoggerFactory.getLogger(DataRetrieveImpl.class);

    @Autowired
    SubmissionService submissionService;

    @Autowired
    QueueService<Submission> queue;

    @Scheduled(fixedDelay = 4000)
    public void scheduledRetrieve() {
        int required = 2;
        if (required != 0) {
            List<Submission> submissions = submissionService.getSubmissionList(required);
            queue.add(submissions);
            List<Long> idList = submissions.stream()
                    .map(Submission::getId)
                    .collect(Collectors.toList());

            submissionService.submissionUpdate(idList);
        }
    }
}
