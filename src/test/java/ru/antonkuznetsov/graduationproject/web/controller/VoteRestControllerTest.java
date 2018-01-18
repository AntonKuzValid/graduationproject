package ru.antonkuznetsov.graduationproject.web.controller;

import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.antonkuznetsov.graduationproject.TestUtil;
import ru.antonkuznetsov.graduationproject.model.Vote;
import ru.antonkuznetsov.graduationproject.service.interfaces.VoteService;
import ru.antonkuznetsov.graduationproject.web.AbstractControllerTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.antonkuznetsov.graduationproject.MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE;
import static ru.antonkuznetsov.graduationproject.MenuTestData.MENU_ID_RESTAURANT_2_CURRENT_DATE;
import static ru.antonkuznetsov.graduationproject.UserTestData.USER_1;
import static ru.antonkuznetsov.graduationproject.UserTestData.USER_2;
import static ru.antonkuznetsov.graduationproject.VoteTestData.*;

public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService voteService;

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER_1)))
                .andExpect(contentJson(VOTE_USER_1));
    }

    @Test
    public void changeVote() throws Exception {
        //        User change vote
        LocalTime votingTime = LocalTime.of(10, 0);

        new Expectations(LocalTime.class) {{
            LocalTime.now();
            result = votingTime;
        }};

        mockMvc.perform(put(REST_URL + "vote")
                .with(userHttpBasic(USER_1))
                .param("menuId", String.valueOf(MENU_ID_RESTAURANT_2_CURRENT_DATE)))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(voteService.getAllByDate(LocalDate.now()),
                Arrays.asList(NEW_VOTE_USER_1));

        Assert.assertEquals(voteService.getRating(MENU_ID_RESTAURANT_1_CURRENT_DATE), 0);
        Assert.assertEquals(voteService.getRating(MENU_ID_RESTAURANT_2_CURRENT_DATE), 1);
    }

    @Test
    public void isNotAbleVote() throws Exception {
        //        User can't vote
        LocalTime newVotingTime = LocalTime.of(12, 0);

        new Expectations(LocalTime.class) {{
            LocalTime.now();
            result = newVotingTime;
        }};

        mockMvc.perform(put(REST_URL + "vote")
                .with(userHttpBasic(USER_1))
                .param("menuId", String.valueOf(MENU_ID_RESTAURANT_1_CURRENT_DATE)))
                .andExpect(status().isForbidden())
                .andDo(print());

        assertMatch(voteService.getAllByDate(LocalDate.now()),
                Arrays.asList(NEW_VOTE_USER_1));

        Assert.assertEquals(voteService.getRating(MENU_ID_RESTAURANT_1_CURRENT_DATE), 1);
        Assert.assertEquals(voteService.getRating(MENU_ID_RESTAURANT_2_CURRENT_DATE), 0);
    }

    @Test
    public void newVote() throws Exception {
        //        User didn't vote yet
        Vote created=NEW_VOTE_USER_1;

        ResultActions result=mockMvc.perform(put(REST_URL + "vote")
                .with(userHttpBasic(USER_2))
                .param("menuId", String.valueOf(MENU_ID_RESTAURANT_1_CURRENT_DATE)))
                .andExpect(status().isOk())
                .andDo(print());

        Vote returned = TestUtil.readFromJson(result, Vote.class);
        created.setId(returned.getId());

        assertMatch(voteService.getAllByDate(LocalDate.now()),
                Arrays.asList(VOTE_USER_1, created));

        Assert.assertEquals(voteService.getRating(MENU_ID_RESTAURANT_1_CURRENT_DATE), 2);
        Assert.assertEquals(voteService.getRating(MENU_ID_RESTAURANT_2_CURRENT_DATE), 0);
    }
}