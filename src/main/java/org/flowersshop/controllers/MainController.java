package org.flowersshop.controllers;

import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/customersData", method = RequestMethod.GET)
    public ModelAndView getCustomers() throws EmptyResultSetException {
        return new ModelAndView("customersData","customer_list", customerService.allUsers());
    }

    @RequestMapping("/customers")
    public String customerAjax() {
        return "customers";
    }

}