package com.muztaba.service.entity;

import com.muztaba.entity.Submission;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by seal on 8/19/16.
 */
@Service
@Transactional
public class SubmissionImpl implements SubmissionService {

    @Autowired
    SessionFactory sessionFactory;

    private static final int DEFAULT_RETRIEVE_RESULT = 5;
    
    @Override
    public void post(Submission submission) {
        sessionFactory.getCurrentSession()
                .save(submission);
    }

    @Override
    public Submission get(long id) {
        return sessionFactory.getCurrentSession()
                .get(Submission.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Submission> getSubmissionList() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Submission.class)
                .add(Restrictions.eq("status", false))
                .addOrder(Order.asc("time"))
                .setMaxResults(DEFAULT_RETRIEVE_RESULT)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Submission> getSubmissionList(int size) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Submission.class)
                .add(Restrictions.eq("status", false))
                .addOrder(Order.asc("time"))
                .setMaxResults(size)
                .list();
    }

    @Override
    public void submissionUpdate(List<Long> list) {
        for (long i : list) {
            Submission submission = sessionFactory.getCurrentSession().load(Submission.class, i);
            submission.setStatus(true);
            sessionFactory.getCurrentSession().update(submission);
        }
    }
}
