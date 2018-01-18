package ru.antonkuznetsov.graduationproject;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.antonkuznetsov.graduationproject.RestaurantsTestData.*;
import static ru.antonkuznetsov.graduationproject.web.json.JsonUtil.writeIgnoreProps;

public class MenuTestData {
    public static final Menu MENU_RESTAURANT_1_CURRENT_DATE = new Menu(100004, LocalDate.now(), RESTAURANT_1);
    public static final int MENU_ID_RESTAURANT_1_CURRENT_DATE=MENU_RESTAURANT_1_CURRENT_DATE.getId();
    public static final Menu MENU_RESTAURANT_1 = new Menu(100005, LocalDate.of(2018,1,1), RESTAURANT_1);
    public static final Menu MENU_RESTAURANT_2_CURRENT_DATE = new Menu(100006, LocalDate.now(), RESTAURANT_2);
    public static final int MENU_ID_RESTAURANT_2_CURRENT_DATE=MENU_RESTAURANT_2_CURRENT_DATE.getId();

    public static final Menu CREATED_MENU=new Menu(null,LocalDate.of(2018,2,2),null);

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Menu... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "restaurant"));
    }

    public static ResultMatcher contentJson(Menu expected) {
        return content().json(writeIgnoreProps(expected, "restaurant"));
    }
}
