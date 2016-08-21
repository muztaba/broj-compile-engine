package com.muztaba.service;

import com.muztaba.model.Verdict;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by seal on 8/22/16.
 */
@Service
public class VerdictServiceImpl implements VerdictService {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void post(Verdict verdict) {
        sessionFactory.getCurrentSession()
                .save(verdict);
    }
}
