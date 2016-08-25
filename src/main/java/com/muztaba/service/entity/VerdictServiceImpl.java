package com.muztaba.service.entity;

import com.muztaba.entity.Verdict;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by seal on 8/22/16.
 */
@Service
@Transactional
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VerdictServiceImpl implements VerdictService {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void post(Verdict verdict) {
        sessionFactory.getCurrentSession()
                .save(verdict);
    }
}
