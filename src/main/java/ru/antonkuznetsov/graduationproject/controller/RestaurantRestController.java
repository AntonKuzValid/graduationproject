package ru.antonkuznetsov.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.RestaurantService;
import ru.antonkuznetsov.graduationproject.service.VoteService;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithScore;

import java.util.List;

@RestController
public class RestaurantRestController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    VoteService voteService;

    public List<RestaurantWithScore> vote(Restaurant restaurant, int userId) {
        voteService.vote(restaurant, userId);
        return restaurantService.getAll();
    }
}
