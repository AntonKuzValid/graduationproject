package ru.antonkuznetsov.graduationproject;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.antonkuznetsov.graduationproject.MenuTestData.MENU_RESTAURANT_1_CURRENT_DATE;
import static ru.antonkuznetsov.graduationproject.MenuTestData.MENU_RESTAURANT_2_CURRENT_DATE;
import static ru.antonkuznetsov.graduationproject.UserTestData.USER_1;
import static ru.antonkuznetsov.graduationproject.UserTestData.USER_2;
import static ru.antonkuznetsov.graduationproject.web.json.JsonUtil.writeIgnoreProps;

public class VoteTestData {

    public static final Vote VOTE_USER_1 = new Vote(100016, LocalDate.now(), USER_1, MENU_RESTAURANT_1_CURRENT_DATE);
    public static final Vote VOTE_USER_2 = new Vote(100019, LocalDate.now(), USER_2, MENU_RESTAURANT_1_CURRENT_DATE);
    public static final Vote NEW_VOTE_USER_1 = new Vote(100016, LocalDate.now(), USER_1, MENU_RESTAURANT_2_CURRENT_DATE);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "menu");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "menu").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Vote... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "user"));
    }

    public static ResultMatcher contentJson(Vote expected) {
        return content().json(writeIgnoreProps(expected, "user"));
    }
}
