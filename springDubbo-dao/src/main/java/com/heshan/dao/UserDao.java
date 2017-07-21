package com.heshan.dao;

import com.heshan.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/24 15:56
 */
public interface  UserDao {

    public void  insert(User user);
    public List<User> findPage(User user);
    public List<User> findUser(Map map);

}
