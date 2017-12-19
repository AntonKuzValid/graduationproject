package ru.antonkuznetsov.graduationproject.service;

import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithScore;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(Restaurant restaurant);

    RestaurantWithScore get(int id);

    List<RestaurantWithScore> getAll();
}
