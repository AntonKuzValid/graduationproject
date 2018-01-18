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
import ru.antonkuznetsov.graduationproject.DishTestData;
import ru.antonkuznetsov.graduationproject.MenuTestData;
import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.service.interfaces.DishService;

import static org.junit.Assert.*;
import static ru.antonkuznetsov.graduationproject.DishTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceImplTest {

    @Autowired
    DishService dishService;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("dishes").clear();
    }

    @Test
    public void get() throws Exception {
        assertMatch(dishService.get(DISH1.getId()),DISH1);
    }

    @Test
    public void getByMenuId() throws Exception {
        assertMatch(dishService.getByMenuId(MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE),DISH1,DISH2);
    }

    @Test
    public void create() throws Exception {
        Dish created=dishService.create(CREATED_DISH,MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE);
        assertMatch(dishService.getByMenuId(MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE), DISH1,DISH2,created);
    }

    @Test
    public void update() throws Exception {
        Dish updated=dishService.update(UPDATED_DISH, MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE);
        assertMatch(dishService.getByMenuId(MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE), DISH2, updated);
    }

    @Test
    public void delete() throws Exception {
        dishService.delete(DISH1.getId());
        assertMatch(dishService.getByMenuId(MenuTestData.MENU_ID_RESTAURANT_1_CURRENT_DATE), DISH2);
    }

}