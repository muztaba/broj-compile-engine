package com.muztaba.service;

import com.muztaba.model.Problem;

/**
 * Created by seal on 8/19/16.
 */
public interface ProblemService {

    Problem load(long id);

    void post(Problem problem);

}
