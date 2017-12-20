package ru.antonkuznetsov.graduationproject.to;

import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.model.Restaurant;

import java.util.List;

public class RestaurantWithRating {
    private Integer id;
    private String name;
    private Integer rating;
    private List<Dish> lunchMenu;

    public RestaurantWithRating(Integer id, String name, Integer rating, List<Dish> lunchMenu) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.lunchMenu = lunchMenu;

    }

    public RestaurantWithRating(Restaurant restaurant, int rating) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.rating = rating;
        this.lunchMenu = restaurant.getMenu();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<Dish> getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(List<Dish> lunchMenu) {
        this.lunchMenu = lunchMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RestaurantWithRating{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating;
    }
}
