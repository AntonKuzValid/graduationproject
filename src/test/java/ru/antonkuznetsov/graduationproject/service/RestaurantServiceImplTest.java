package ru.antonkuznetsov.graduationproject.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.antonkuznetsov.graduationproject.RestaurantsTestData;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;

import static org.junit.Assert.*;
import static ru.antonkuznetsov.graduationproject.RestaurantsTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceImplTest {
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
    }

    @Test
    public void create() throws Exception {
        Restaurant created=restaurantService.create(NEW_RESTAURANT);
        assertMatch(restaurantService.getAll(), RESTAURANT_1,RESTAURANT_2,created);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated=restaurantService.update(UPDATED_RESTAURANT);
        assertMatch(restaurantService.getAll(),UPDATED_RESTAURANT,RESTAURANT_2);
    }

    @Test
    public void delete() throws Exception {
        restaurantService.delete(RESTAURANT_ID_1);
        assertMatch(restaurantService.getAll(), RESTAURANT_2);
    }

    @Test
    public void get() throws Exception {
        assertMatch(restaurantService.get(RESTAURANT_ID_1),RESTAURANT_1);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(restaurantService.getAll(),RESTAURANT_1,RESTAURANT_2);
    }

}