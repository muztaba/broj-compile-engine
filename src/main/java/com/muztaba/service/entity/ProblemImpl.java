package com.muztaba.service.entity;

import com.muztaba.entity.Problem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by seal on 8/19/16.
 */
@Service
@Transactional
public class ProblemImpl implements ProblemService {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Problem load(long id) {
        return sessionFactory.getCurrentSession()
                .load(Problem.class, id);
    }

    @Override
    public void post(Problem problem) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(problem);
    }

    @Override
    public Problem get(long id) {
        return sessionFactory.getCurrentSession()
                .get(Problem.class, id);
    }
}
