package ru.antonkuznetsov.graduationproject;

import ru.antonkuznetsov.graduationproject.model.*;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {
    public static final Restaurant RESTAURANT_1 = new Restaurant(100000, "BK", null);
    public static final int RESTAURANT_ID_1 = RESTAURANT_1.getId();
    public static final Restaurant RESTAURANT_2 = new Restaurant(100001, "KFC", null);
    public static final int RESTAURANT_ID_2 = RESTAURANT_2.getId();

    public static final User USER_1 = new User(100002, "User", "user@yandex.ru", "password", null, Collections.singleton(Role.ROLE_USER));
    public static final User USER_2 = new User(100003, "Admin", "admin@gmail.com", "admin", null, Collections.singleton(Role.ROLE_ADMIN));

    static {
        RESTAURANT_1.setMenu(Arrays.asList(new Dish(100004, "meat", 200, RESTAURANT_1)));
        RESTAURANT_2.setMenu(Arrays.asList(new Dish(100005, "potatoe", 100, RESTAURANT_2)));

        USER_2.setVote(new Vote(100007, LocalDate.now(),USER_2,RESTAURANT_2));
    }

    public static final RestaurantWithRating RESTAURANT_WITH_RATING_2 = new RestaurantWithRating(RESTAURANT_2, 1);

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
