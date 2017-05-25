package com.shud.service.serviceImpl;

import com.shud.mapper.UserMapper;
import com.shud.model.User;
import com.shud.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by shud on 2017/5/19.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Transactional
    @Cacheable(value = "users", key = "'getUser_'+#id")
    public User getUser(int id) {
        logger.info("enter Service to find user");
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            return user;
        } else {
            logger.info("user is null");
            return null;
        }
    }

    @Override
    @CacheEvict(value = "users" , key = "'getUser_'+#id")
    public int deleteUser(int id) {
        int status = userMapper.deleteByPrimaryKey(id);
        return status;
    }
}
