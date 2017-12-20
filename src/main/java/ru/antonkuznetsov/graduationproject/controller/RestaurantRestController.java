package ru.antonkuznetsov.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.RestaurantService;
import ru.antonkuznetsov.graduationproject.service.VoteService;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    VoteService voteService;

    @GetMapping()
    public ResponseEntity<List<RestaurantWithRating>> getAll() {
        return new ResponseEntity<>(restaurantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<Dish>> getLunchMenu(@PathVariable int restaurantId) {
        RestaurantWithRating restaurant = restaurantService.getRestaurantWithMenu(restaurantId);
        return new ResponseEntity<>(restaurant.getLunchMenu(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Restaurant> update(@ModelAttribute("restaurant") Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.update(restaurant);
        return newRestaurant != null ? new ResponseEntity<>(newRestaurant, HttpStatus.CREATED) :
                new ResponseEntity<Restaurant>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> create(@ModelAttribute("restaurant") Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.create(restaurant);
        return newRestaurant != null ? new ResponseEntity<>(newRestaurant, HttpStatus.CREATED) :
                new ResponseEntity<Restaurant>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/vote/{restaurantId}")
    public ResponseEntity<RestaurantWithRating> vote(@PathVariable int restaurantId, int userId) {
        voteService.vote(restaurantId, userId);
        return new ResponseEntity<>(restaurantService.get(userId), HttpStatus.OK);
    }
}
