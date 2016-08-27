package com.muztaba.service.entity;

import com.muztaba.entity.Problem;

/**
 * Created by seal on 8/19/16.
 */
public interface ProblemService {

    Problem load(long id);

    void post(Problem problem);

    Problem get(long id);
}
