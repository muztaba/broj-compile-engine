package com.muztaba.service;

import com.muztaba.model.User;

import java.util.List;

/**
 * Created by seal on 8/13/2016.
 */
public interface UserService {

    void post(User user);

    User get(long id);

    List<User> getUserList();

    void usersUpdate(List<Long> list);
}
