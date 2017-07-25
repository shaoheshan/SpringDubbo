package com.heshan.dubbo.dao;

import com.heshan.dubbo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/24 15:56
 */
@Repository
public interface  UserDao {

    public void  insert(User user);
    public List<User> findPage(User user);
    public List<User> findUser(Map map);

}
