package com.muztaba.service;

import com.muztaba.model.Submission;
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
                .setMaxResults(5)
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
