package ru.antonkuznetsov.graduationproject.service.interfaces;

import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.util.List;

public interface RestaurantService {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int restaurantId);

    RestaurantWithRating getWithRating(int id);

    List<RestaurantWithRating> getAllWithRating();
}
