package com.shud.controller;

import com.shud.model.Admin;
import com.shud.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shud on 2017/5/23.
 */
@RestController
@RequestMapping("/admin")
public class AdminRestfulController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/queryAdmin/{id}")
    @ResponseBody
    public Admin queryAdmin(@PathVariable("id") int id){
        return adminService.getAdmin(id);
    }

}
