package ru.antonkuznetsov.graduationproject.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.antonkuznetsov.graduationproject.MenuTestData;
import ru.antonkuznetsov.graduationproject.model.Vote;
import ru.antonkuznetsov.graduationproject.service.interfaces.VoteService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static ru.antonkuznetsov.graduationproject.UserTestData.*;
import static ru.antonkuznetsov.graduationproject.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceImplTest {

    @Autowired
    VoteService voteService;

    @Test
    public void getByUser() throws Exception {
        Vote vote=voteService.getByUser(USER_1_ID, LocalDate.now());
        assertMatch(vote, VOTE_USER_1);
        Assert.assertEquals(vote.getMenu(), VOTE_USER_1.getMenu());
    }

    @Test
    public void getAllByDate() throws Exception {
        List<Vote> votes=voteService.getAllByDate(LocalDate.now());
        assertMatch(votes, Arrays.asList(VOTE_USER_1));
    }

    @Test
    public void getRating() throws Exception {
        int rating=voteService.getRating(MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE);
        Assert.assertEquals(rating,1);
    }

}