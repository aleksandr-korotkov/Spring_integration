package org.flowersshop.controllers;

import org.flowersshop.entities.Customer;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private CustomerService customerService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("customerForm", new Customer());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("customer") @Valid Customer customerForm, BindingResult bindingResult, Model model) throws EmptyResultSetException {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!customerForm.getPassword().equals(customerForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!customerService.saveUser(customerForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}