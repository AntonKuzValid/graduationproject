package ru.antonkuznetsov.graduationproject.service.interfaces;

import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;

import java.util.List;

public interface MenuService {

    Menu get(int id);

    List <Menu> getAll(int RestaurantId);

    Menu create(Menu menu, int restaurantId);

    void delete(int id);
}
