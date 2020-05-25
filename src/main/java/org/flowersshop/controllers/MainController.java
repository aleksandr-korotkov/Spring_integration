package org.flowersshop.controllers;

import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.services.CustomerService;
import org.flowersshop.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private CustomerService customerService;


    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "empty page");
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(ModelMap model) {
        model.addAttribute("message", "home page");
        return "home";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(ModelMap model) throws EmptyResultSetException {
        model.addAttribute("customer_list", customerService.allUsers());
        return "customers";
    }


}