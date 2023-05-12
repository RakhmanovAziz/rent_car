package com.example.demo.services;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class UserService {
    @Autowired
    private PersonRepository repo;
    public void save(Person user) {
        repo.save(user);
    }
}

