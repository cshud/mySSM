package com.shud.service;

import com.shud.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by shud on 2017/5/19.
 */
public interface UserService {
    User getUser(int id);
    int deleteUser(int id);
}
