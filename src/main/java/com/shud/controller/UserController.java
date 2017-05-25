package com.shud.controller;

import com.shud.model.User;
import com.shud.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shud on 2017/5/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/queryUser")
    public String toIndex(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "test";
    }
}
