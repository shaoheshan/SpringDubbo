package com.heshan.dubbo.service.impl;

import com.heshan.dao.UserDao;
import com.heshan.dubbo.service.UserService;
import com.heshan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/24 16:07
 */
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    UserDao userDao;

    @Override
    public List<User> findPage(User user) {
        return userDao.findPage(user);
    }

    public void save(User user) {

        userDao.insert(user);
    }
    @Override
    public List<User> findUser(Map<String, Object> map) {
        return userDao.findUser(map);
    }
}
