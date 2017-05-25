package com.shud.service.serviceImpl;

import com.shud.mapper.AdminMapper;
import com.shud.model.Admin;
import com.shud.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shud on 2017/5/23.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin(int id) {
        Admin admin =  adminMapper.selectByPrimaryKey(id);
        if(admin==null){
            return null;
        }
        return admin;
    }
}
