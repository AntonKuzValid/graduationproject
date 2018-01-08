package ru.antonkuznetsov.graduationproject.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;
import ru.antonkuznetsov.graduationproject.util.ValidationUtil;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/restaurants";

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> get(@PathVariable("restaurantId") int restaurantId) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        log.info("get restaurant with id {}", restaurantId);
        return restaurant != null ?
                new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK) :
                ResponseEntity.notFound().build();
    }

    @GetMapping()
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    @GetMapping("/rating/{restaurantId}")
    public RestaurantWithRating getWithRating(@PathVariable("restaurantId") int restaurantId) {
        log.info("get restaurant and rating with id {}", restaurantId);
        return restaurantService.getWithRating(restaurantId);
    }

    @GetMapping("/rating")
    public List<RestaurantWithRating> getAllWithRating() {
        log.info("get all restaurants and rating");
        return restaurantService.getAllWithRating();
    }

    @DeleteMapping("/{restaurantId}")
    public void delete(@PathVariable("restaurantId") int restaurantId) {
        log.info("delete restaurant with {}", restaurantId);
        restaurantService.delete(restaurantId);
    }

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("restaurantId") int restaurantId) {
        ValidationUtil.assureIdConsistent(restaurant, restaurantId);
        log.info("update restaurant with {}", restaurantId);
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return restaurantService.create(restaurant);
    }
}
