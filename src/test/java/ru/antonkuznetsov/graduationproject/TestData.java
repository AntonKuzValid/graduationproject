package ru.antonkuznetsov.graduationproject;

import ru.antonkuznetsov.graduationproject.model.*;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {
    public static final boolean isAbleVote= !LocalTime.now().isAfter(LocalTime.of(11,00));

    public static final Restaurant RESTAURANT_1 = new Restaurant(100000, "BK");
    public static final int RESTAURANT_ID_1 = RESTAURANT_1.getId();
    public static final Restaurant RESTAURANT_2 = new Restaurant(100001, "KFC");
    public static final int RESTAURANT_ID_2 = RESTAURANT_2.getId();

    public static final Menu MENU_RESTAURANT_1_CURRENT_DATE = new Menu(100004, LocalDate.now(), RESTAURANT_1);
    public static final int MENU_ID_RESTAURANT_1_CURRENT_DATE=MENU_RESTAURANT_1_CURRENT_DATE.getId();
    public static final Menu MENU_RESTAURANT_2_CURRENT_DATE = new Menu(100006, LocalDate.now(), RESTAURANT_2);
    public static final int MENU_ID_RESTAURANT_2_CURRENT_DATE=MENU_RESTAURANT_2_CURRENT_DATE.getId();

    public static final Dish DISH1 = new Dish(100008, "meat", 200, MENU_RESTAURANT_1_CURRENT_DATE);
    public static final Dish DISH2 = new Dish(100009, "vine", 500, MENU_RESTAURANT_1_CURRENT_DATE);
    public static final Dish DISH3 = new Dish(100010, "potato", 100, MENU_RESTAURANT_2_CURRENT_DATE);
    public static final Dish DISH4 = new Dish(100011, "juice", 100, MENU_RESTAURANT_2_CURRENT_DATE);

    public static final User USER_1 = new User(100002, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final int USER_1_ID = USER_1.getId();
    public static final User USER_2 = new User(100003, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final int USER_2_ID = USER_2.getId();

    public static final Vote VOTE_USER_1 = new Vote(100016, LocalDate.now(), USER_1, MENU_RESTAURANT_1_CURRENT_DATE);
    public static final Vote NEW_VOTE_USER_1=new Vote(100016, LocalDate.now(), USER_1,MENU_RESTAURANT_2_CURRENT_DATE);

    public static final RestaurantWithRating RESTAURANT_WITH_RATING_1 = new RestaurantWithRating(RESTAURANT_1, 1);
    public static final RestaurantWithRating RESTAURANT_WITH_RATING_2 = new RestaurantWithRating(RESTAURANT_2, 0);

    public static final RestaurantWithRating NEW_RESTAURANT_WITH_RATING_1 = new RestaurantWithRating(RESTAURANT_1, 0);
    public static final RestaurantWithRating NEW_RESTAURANT_WITH_RATING_2 = new RestaurantWithRating(RESTAURANT_2, 1);
    public static final RestaurantWithRating NEW_RESTAURANT_WITH_RATING_3 = new RestaurantWithRating(RESTAURANT_1, 1);

    public static void assertMatch(RestaurantWithRating actual, RestaurantWithRating expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "lunchMenu");
    }

    public static void assertMatch(Iterable<RestaurantWithRating> actual, RestaurantWithRating... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<RestaurantWithRating> actual, Iterable<RestaurantWithRating> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("lunchMenu").isEqualTo(expected);
    }
}
