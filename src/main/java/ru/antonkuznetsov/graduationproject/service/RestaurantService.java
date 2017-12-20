package ru.antonkuznetsov.graduationproject.service;

import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(Restaurant restaurant);

    RestaurantWithRating get(int id);

    List<RestaurantWithRating> getAll();

    RestaurantWithRating getRestaurantWithMenu(int id);
}
