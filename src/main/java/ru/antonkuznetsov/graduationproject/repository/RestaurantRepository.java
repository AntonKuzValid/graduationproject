package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonkuznetsov.graduationproject.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
