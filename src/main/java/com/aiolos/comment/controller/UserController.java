package com.aiolos.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Aiolos
 * @date 2019-10-11 23:10
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name", "aiolos");
        return modelAndView;
    }
}
