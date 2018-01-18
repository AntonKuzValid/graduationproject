package ru.antonkuznetsov.graduationproject;

import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.model.Vote;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantsTestData {
    public static final Restaurant RESTAURANT_1 = new Restaurant(100000, "BK");
    public static final int RESTAURANT_ID_1 = RESTAURANT_1.getId();
    public static final Restaurant RESTAURANT_2 = new Restaurant(100001, "KFC");
    public static final int RESTAURANT_ID_2 = RESTAURANT_2.getId();

    public static final Restaurant NEW_RESTAURANT=new Restaurant(null, "Mac");
    public static final Restaurant UPDATED_RESTAURANT=new Restaurant(100000,"Pizza");

    public static final RestaurantWithRating RESTAURANT_WITH_RATING_1 = new RestaurantWithRating(RESTAURANT_1, 1);
    public static final RestaurantWithRating RESTAURANT_WITH_RATING_2 = new RestaurantWithRating(RESTAURANT_2, 0);

    public static final RestaurantWithRating NEW_RESTAURANT_WITH_RATING_1 = new RestaurantWithRating(RESTAURANT_1, 0);
    public static final RestaurantWithRating NEW_RESTAURANT_WITH_RATING_2 = new RestaurantWithRating(RESTAURANT_2, 1);
    public static final RestaurantWithRating NEW_RESTAURANT_WITH_RATING_3 = new RestaurantWithRating(RESTAURANT_1, 1);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
