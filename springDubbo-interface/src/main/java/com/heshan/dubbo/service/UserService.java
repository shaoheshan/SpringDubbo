package com.heshan.dubbo.service;

import com.heshan.dubbo.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/24 15:56
 */
public interface UserService {

    public void  save(User user);

    List<User> findPage(User user);

    List<User> findUser(Map<String, Object> map);
}
