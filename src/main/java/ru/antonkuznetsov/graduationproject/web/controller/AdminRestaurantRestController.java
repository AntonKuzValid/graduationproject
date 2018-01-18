package ru.antonkuznetsov.graduationproject.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.interfaces.DishService;
import ru.antonkuznetsov.graduationproject.service.interfaces.MenuService;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;
import ru.antonkuznetsov.graduationproject.util.ValidationUtil;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL_USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {


    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL_USER = "/rest/admin/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DishService dishService;

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurantId") int restaurantId) {
        log.info("delete restaurant with id {}", restaurantId);
        restaurantService.delete(restaurantId);
    }

    @DeleteMapping("/menu/{menuId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable("menuId") int menuId) {
        log.info("delete menu with id {}", menuId);
        menuService.delete(menuId);
    }

    @DeleteMapping("/menu/dish/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable("dishId") int dishId) {
        log.info("delete menu with id {}");
        dishService.delete(dishId);
    }

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant,
                       @PathVariable("restaurantId") int restaurantId) {
        ValidationUtil.assureIdConsistent(restaurant, restaurantId);
        log.info("update restaurant with {}", restaurantId);
        restaurantService.update(restaurant);
    }

    @PutMapping(value = "/menu/{menuId}/dish/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDish(@RequestBody Dish dish,
                           @PathVariable("menuId") int menuId,
                           @PathVariable("dishId") int dishId) {
        ValidationUtil.assureIdConsistent(dish, dishId);
        log.info("update menu with id {}", dishId);
        dishService.update(dish, menuId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return restaurantService.create(restaurant);
    }

    @PostMapping(value = "/{restaurantId}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu createMenu(@RequestBody Menu menu,
                           @PathVariable("restaurantId") int restaurantId) {
        ValidationUtil.checkNew(menu);
        log.info("create menu with id {}");
        return menuService.create(menu, restaurantId);
    }

    @PostMapping(value = "/menu/{menuId}/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish createDish(@RequestBody Dish dish,
                           @PathVariable("menuId") int menuId) {
        ValidationUtil.checkNew(dish);
        log.info("create menu with id {}");
        return dishService.create(dish, menuId);
    }
}
