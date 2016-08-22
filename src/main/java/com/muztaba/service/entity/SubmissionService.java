package com.muztaba.service.entity;

import com.muztaba.entity.Submission;

import java.util.List;

/**
 * Created by seal on 8/19/16.
 */
public interface SubmissionService {

    void post(Submission submission);

    Submission get(long id);

    List<Submission> getSubmissionList();

    List<Submission> getSubmissionList(int size);

    void submissionUpdate(List<Long> list);

}
