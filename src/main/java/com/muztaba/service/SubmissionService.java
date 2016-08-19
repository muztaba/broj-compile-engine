package com.muztaba.service;

import com.muztaba.model.Submission;

import java.util.List;

/**
 * Created by seal on 8/19/16.
 */
public interface SubmissionService {

    void post(Submission submission);

    Submission get(long id);

    List<Submission> getSubmissionList();

    void submissionUpdate(List<Long> list);

}
