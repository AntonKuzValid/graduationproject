package ru.antonkuznetsov.graduationproject.web.controller;

import mockit.Expectations;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.antonkuznetsov.graduationproject.AuthorizedUser;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;
import ru.antonkuznetsov.graduationproject.service.interfaces.VoteService;
import ru.antonkuznetsov.graduationproject.web.AbstractControllerTest;

import java.time.LocalTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.antonkuznetsov.graduationproject.TestData.*;

public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService voteService;

    @Test
    public void vote() throws Exception {
//        User change vote
        LocalTime votingTime = LocalTime.of(10, 0);

        new Expectations(LocalTime.class) {{
            LocalTime.now();
            result = votingTime;
        }};

        mockMvc.perform(get(REST_URL + "vote/" + MENU_ID_RESTAURANT_2_CURRENT_DATE))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(restaurantService.getAllWithRating(),
                Arrays.asList(NEW_RESTAURANT_WITH_RATING_1, NEW_RESTAURANT_WITH_RATING_2));

//        User can't vote
        LocalTime newVotingTime = LocalTime.of(12, 0);

        new Expectations(LocalTime.class) {{
            LocalTime.now();
            result = newVotingTime;
        }};

        mockMvc.perform(get(REST_URL + "vote/" + MENU_ID_RESTAURANT_1_CURRENT_DATE))
                .andExpect(status().isForbidden())
                .andDo(print());

        assertMatch(restaurantService.getAllWithRating(),
                Arrays.asList(NEW_RESTAURANT_WITH_RATING_1, NEW_RESTAURANT_WITH_RATING_2));

//        User didn't vote yet
        AuthorizedUser.setId(USER_2_ID);
        mockMvc.perform(get(REST_URL + "vote/" + MENU_ID_RESTAURANT_1_CURRENT_DATE))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(restaurantService.getAllWithRating(),
                Arrays.asList(NEW_RESTAURANT_WITH_RATING_3, NEW_RESTAURANT_WITH_RATING_2));
    }

}