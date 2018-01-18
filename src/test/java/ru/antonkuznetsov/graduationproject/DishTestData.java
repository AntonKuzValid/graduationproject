package ru.antonkuznetsov.graduationproject;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.model.Menu;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.antonkuznetsov.graduationproject.MenuTestData.MENU_RESTAURANT_1_CURRENT_DATE;
import static ru.antonkuznetsov.graduationproject.MenuTestData.MENU_RESTAURANT_2_CURRENT_DATE;
import static ru.antonkuznetsov.graduationproject.web.json.JsonUtil.writeIgnoreProps;

public class DishTestData {
    public static final Dish DISH1 = new Dish(100008, "meat", 200, MENU_RESTAURANT_1_CURRENT_DATE);
    public static final Dish DISH2 = new Dish(100009, "vine", 500, MENU_RESTAURANT_1_CURRENT_DATE);
    public static final Dish DISH3 = new Dish(100010, "potato", 100, MENU_RESTAURANT_2_CURRENT_DATE);
    public static final Dish DISH4 = new Dish(100011, "juice", 100, MENU_RESTAURANT_2_CURRENT_DATE);

    public static final Dish CREATED_DISH=new Dish(null,"created", 200,null);
    public static final Dish UPDATED_DISH=new Dish(100008,"updated", 200,MENU_RESTAURANT_1_CURRENT_DATE);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Dish... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "menu"));
    }

    public static ResultMatcher contentJson(Dish expected) {
        return content().json(writeIgnoreProps(expected, "menu"));
    }
}
