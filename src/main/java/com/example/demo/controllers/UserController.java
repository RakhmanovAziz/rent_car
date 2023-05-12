package com.example.demo.controllers;


import com.example.demo.model.Person;
import com.example.demo.services.RegistrationService;
import com.example.demo.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;

    @Autowired
    public UserController(PersonValidator personValidator,  RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }



    @GetMapping("/login")
    public String login(){
        return "/login";
    }


    //новый
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "registration";
    }


    //новый
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "/registration";
        registrationService.register(person);
        return "redirect:/login";
    }





    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }

    


} 
