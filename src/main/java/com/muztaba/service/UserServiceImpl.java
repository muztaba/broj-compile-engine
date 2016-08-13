package com.muztaba.service;

import com.muztaba.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by seal on 8/13/2016.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    SessionFactory sessionFactory;

    public void post(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User get(long id) {
        return sessionFactory.getCurrentSession()
                .load(User.class, id);
    }
}
