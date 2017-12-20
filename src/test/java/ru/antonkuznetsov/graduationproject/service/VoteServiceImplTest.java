package ru.antonkuznetsov.graduationproject.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static ru.antonkuznetsov.graduationproject.TestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceImplTest {

    @Autowired
    VoteService voteService;
    @Autowired
    RestaurantService restaurantService;

    @Test
    public void vote() throws Exception {
        voteService.vote(RESTAURANT_ID_2, USER_2.getId());
        assertMatch(restaurantService.get(RESTAURANT_ID_2), RESTAURANT_WITH_RATING_2);
    }

}