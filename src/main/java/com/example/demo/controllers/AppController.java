package com.example.demo.controllers;

import java.util.List;


import com.example.demo.model.Car;
import com.example.demo.model.Person;
import com.example.demo.services.CarService;
import com.example.demo.services.RegistrationService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired; // Определяем класс или поле в качестве главного контейнера, связывающего остальные зависимости между друг другом
import org.springframework.data.repository.query.Param; // Привязываем передачу параметров из SQL-запроса
import org.springframework.stereotype.Controller; // Задаем класс в качестве контроллера в рамках концепции MVC
import org.springframework.ui.Model; // Интерфейс, предоставляющий функционал для реализации модели в рамках концепции MVC
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView; // Хранит функционал для реализации как модели, так и образа в рамках концепции MVC

@Controller
public class AppController {


    private final RegistrationService registrationService;


    private CarService service;
    private final UserService service_user;




    @Autowired
    public AppController(RegistrationService registrationService, CarService service, UserService service_user) {
        this.registrationService = registrationService;
        this.service = service;
        this.service_user = service_user;
    }





    @RequestMapping("/")
    public String viewHelloPage(Model model, @Param("keyword") String keyword) {
        return "hello";
    }

    @RequestMapping("/login")
    public String viewLogin(Model model, @Param("keyword") String keyword) {
        return "login";
    }

    @RequestMapping("/index")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Car> listCars = service.listAll(keyword);
        model.addAttribute("listCars", listCars);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewClientForm(Model model) {
        Car car = new Car();
        model.addAttribute("Car", car);
        return "new_car";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCargo(@ModelAttribute("Car") Car car) {
        service.save(car);
        return "redirect:/index";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditClientForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_car");
        Car car = service.get(id);
        mav.addObject("Car", car);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/index";
    }


    /*
    @RequestMapping("/registration")
    public String viewAuthPage(Model model, @Param("keyword") String keyword) {
        Person user = new Person();
        model.addAttribute("user", user);
        return "registration";
    }

     */

    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") Person user) {
        service_user.save(user);
        return "redirect:/index";
    }




}
