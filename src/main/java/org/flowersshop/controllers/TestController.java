package org.flowersshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "empty page");
        return "index";
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String elcome(ModelMap model) {
        model.addAttribute("message", "home page");
        return "home";
    }

}