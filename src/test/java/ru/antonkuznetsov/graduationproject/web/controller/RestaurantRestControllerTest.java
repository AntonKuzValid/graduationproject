package ru.antonkuznetsov.graduationproject.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.antonkuznetsov.graduationproject.DishTestData;
import ru.antonkuznetsov.graduationproject.MenuTestData;
import ru.antonkuznetsov.graduationproject.TestUtil;
import ru.antonkuznetsov.graduationproject.UserTestData;
import ru.antonkuznetsov.graduationproject.web.AbstractControllerTest;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.antonkuznetsov.graduationproject.DishTestData.DISH1;
import static ru.antonkuznetsov.graduationproject.DishTestData.DISH2;
import static ru.antonkuznetsov.graduationproject.MenuTestData.*;
import static ru.antonkuznetsov.graduationproject.RestaurantsTestData.*;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL_USER + "/";

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
        cacheManager.getCache("dishes").clear();
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(Arrays.asList(RESTAURANT_1, RESTAURANT_2)));
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+RESTAURANT_ID_1)
                .with(userHttpBasic(UserTestData.USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(RESTAURANT_1));
    }

    @Test
    public void getAllMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+RESTAURANT_ID_1+"/menu")
                .with(userHttpBasic(UserTestData.USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.contentJson(MENU_RESTAURANT_1_CURRENT_DATE,MENU_RESTAURANT_1));
    }

    @Test
    public void getMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+"menu/"+MENU_ID_RESTAURANT_1_CURRENT_DATE)
                .with(userHttpBasic(UserTestData.USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.contentJson(MENU_RESTAURANT_1_CURRENT_DATE));
    }

    @Test
    public void getWithRating() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+RESTAURANT_ID_1+"/menu/"+MENU_ID_RESTAURANT_1_CURRENT_DATE+"/rating")
                .with(userHttpBasic(UserTestData.USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(RESTAURANT_WITH_RATING_1));
    }

    @Test
    public void getAllDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+"menu/"+MENU_ID_RESTAURANT_1_CURRENT_DATE+"/dish")
                .with(userHttpBasic(UserTestData.USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.contentJson(DISH1, DISH2));
    }

    @Test
    public void getDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+"menu/"+"dish/"+DISH1.getId())
                .with(userHttpBasic(UserTestData.USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.contentJson(DISH1));
    }

}