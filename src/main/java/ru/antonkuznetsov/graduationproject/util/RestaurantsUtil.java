package ru.antonkuznetsov.graduationproject.util;

import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

public class RestaurantsUtil {
    private RestaurantsUtil(){

    }

    public static RestaurantWithRating getRestaurantWithRating(Restaurant restaurant, int rating){
        return new RestaurantWithRating(restaurant,rating);
    }
}
