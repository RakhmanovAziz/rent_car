package com.example.demo.repositories;

import java.util.List;

import com.example.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT p FROM Car p WHERE CONCAT(p.model, ' ', p.rental_date, ' ', p.receiving_date, ' ', p.client_name) LIKE %?1%")
    List<Car> search(String keyword);
}

