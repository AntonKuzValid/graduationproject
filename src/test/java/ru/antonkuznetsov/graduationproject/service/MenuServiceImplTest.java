package ru.antonkuznetsov.graduationproject.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.antonkuznetsov.graduationproject.MenuTestData;
import ru.antonkuznetsov.graduationproject.RestaurantsTestData;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.interfaces.MenuService;

import static org.junit.Assert.*;
import static ru.antonkuznetsov.graduationproject.MenuTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuServiceImplTest {

    @Autowired
    MenuService menuService;

    @Test
    public void get() throws Exception {
        Menu menu=menuService.get(MENU_ID_RESTAURANT_1_CURRENT_DATE);
        assertMatch(menu,MENU_RESTAURANT_1_CURRENT_DATE);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(menuService.getAll(RestaurantsTestData.RESTAURANT_ID_1),MENU_RESTAURANT_1_CURRENT_DATE, MENU_RESTAURANT_1);
    }

    @Test
    public void create() throws Exception {
        Menu created = menuService.create(CREATED_MENU, RestaurantsTestData.RESTAURANT_ID_1);
        assertMatch(menuService.getAll(RestaurantsTestData.RESTAURANT_ID_1), MENU_RESTAURANT_1_CURRENT_DATE, MENU_RESTAURANT_1,created);
    }

    @Test
    public void delete() throws Exception {
        menuService.delete(MENU_ID_RESTAURANT_1_CURRENT_DATE);
        assertMatch(menuService.getAll(RestaurantsTestData.RESTAURANT_ID_1), MENU_RESTAURANT_1);
    }

}