package ru.antonkuznetsov.graduationproject.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.interfaces.DishService;
import ru.antonkuznetsov.graduationproject.service.interfaces.MenuService;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;
import ru.antonkuznetsov.graduationproject.service.interfaces.VoteService;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;
import ru.antonkuznetsov.graduationproject.util.RestaurantsUtil;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL_USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL_USER = "/rest/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DishService dishService;

    @Autowired
    private VoteService voteService;

    @GetMapping()
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> get(@PathVariable("restaurantId") int restaurantId) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        log.info("get restaurant with id {}", restaurantId);
        return restaurant != null ?
                new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{restaurantId}/menu")
    public List<Menu> getAllMenu(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all menus for the restaurant with id {}", restaurantId);
        return menuService.getAll(restaurantId);
    }

    @GetMapping("/menu/{menuId}")
    public Menu getMenu(@PathVariable("menuId") int menuId) {
        log.info("get menu with id {}", menuId);
        return menuService.get(menuId);
    }

    @GetMapping("/{restaurantId}/menu/{menuId}/rating")
    public RestaurantWithRating getWithRating(@PathVariable("restaurantId") int restaurantId,
                                              @PathVariable("menuId") int menuId) {
        log.info("get restaurant and rating with id {}", restaurantId);
        return RestaurantsUtil.getRestaurantWithRating(restaurantService.get(restaurantId), voteService.getRating(menuId));
    }

    @GetMapping("/menu/{menuId}/dish")
    public List<Dish> getAllDish(@PathVariable("menuId") int menuId) {
        log.info("get all dishes for menu with id {}", menuId);
        return dishService.getByMenuId(menuId);
    }

    @GetMapping("/menu/dish/{dishId}")
    public Dish getDish(@PathVariable("dishId") int dishId) {
        log.info("get dish with id {}", dishId);
        return dishService.get(dishId);
    }
}
