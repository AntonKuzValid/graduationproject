package ru.antonkuznetsov.graduationproject.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.antonkuznetsov.graduationproject.TestUtil;
import ru.antonkuznetsov.graduationproject.UserTestData;
import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.service.interfaces.DishService;
import ru.antonkuznetsov.graduationproject.service.interfaces.MenuService;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;
import ru.antonkuznetsov.graduationproject.web.AbstractControllerTest;
import ru.antonkuznetsov.graduationproject.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.antonkuznetsov.graduationproject.DishTestData.*;
import static ru.antonkuznetsov.graduationproject.DishTestData.assertMatch;
import static ru.antonkuznetsov.graduationproject.MenuTestData.*;
import static ru.antonkuznetsov.graduationproject.MenuTestData.assertMatch;
import static ru.antonkuznetsov.graduationproject.RestaurantsTestData.*;
import static ru.antonkuznetsov.graduationproject.RestaurantsTestData.assertMatch;

public class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantRestController.REST_URL_USER + "/";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DishService dishService;

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID_1)
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isNoContent());

        assertMatch(restaurantService.getAll(), RESTAURANT_2);
    }

    @Test
    public void deleteMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + "menu/" + MENU_ID_RESTAURANT_1_CURRENT_DATE)
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isNoContent());

        assertMatch(menuService.getAll(RESTAURANT_ID_1), MENU_RESTAURANT_1);
    }

    @Test
    public void deleteDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + "menu/dish/" + DISH1.getId())
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isNoContent());

        assertMatch(dishService.getByMenuId(MENU_ID_RESTAURANT_1_CURRENT_DATE), DISH2);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = UPDATED_RESTAURANT;

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isOk());

        assertMatch(restaurantService.getAll(), updated, RESTAURANT_2);
    }

    @Test
    public void updateDish() throws Exception {
        Dish updated = UPDATED_DISH;

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + "menu/" + MENU_ID_RESTAURANT_1_CURRENT_DATE + "/dish/" + DISH1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isOk());

        assertMatch(dishService.getByMenuId(MENU_ID_RESTAURANT_1_CURRENT_DATE), DISH2, updated);
    }

    @Test
    public void create() throws Exception {
        Restaurant created = NEW_RESTAURANT;
        created.setId(null);

        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isOk());

        Restaurant returned = TestUtil.readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(restaurantService.getAll(), RESTAURANT_1, RESTAURANT_2, created);
    }

    @Test
    public void createMenu() throws Exception {
        Menu created = CREATED_MENU;
        created.setId(null);

        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_ID_1 + "/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isOk());

        Menu returned = TestUtil.readFromJson(action, Menu.class);
        created.setId(returned.getId());

        assertMatch(menuService.getAll(RESTAURANT_ID_1), MENU_RESTAURANT_1_CURRENT_DATE, MENU_RESTAURANT_1, created);
    }

    @Test
    public void createDish() throws Exception {
        Dish created = CREATED_DISH;
        created.setId(null);

        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + "menu/" + MENU_ID_RESTAURANT_1_CURRENT_DATE + "/dish")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(UserTestData.USER_2)))
                .andExpect(status().isOk());

        Dish returned = TestUtil.readFromJson(action, Dish.class);
        created.setId(returned.getId());


        assertMatch(dishService.getByMenuId(MENU_ID_RESTAURANT_1_CURRENT_DATE), DISH1, DISH2, created);
    }

}