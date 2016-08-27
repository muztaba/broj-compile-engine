package com.muztaba.service.entity;

import com.muztaba.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        return sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("status", false))
                .addOrder(Order.asc("time"))
                .setMaxResults(5)
                .list();
    }

    public void usersUpdate(List<Long> list) {
        for (Long i : list) {
            User user = sessionFactory.getCurrentSession().load(User.class, i);
            user.setStatus(true);
            sessionFactory.getCurrentSession().update(user);
        }
    }
}
