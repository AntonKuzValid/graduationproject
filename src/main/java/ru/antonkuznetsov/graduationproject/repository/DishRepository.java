package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonkuznetsov.graduationproject.model.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findByRestaurantId(Integer restaurantId);
}
