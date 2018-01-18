package ru.antonkuznetsov.graduationproject.service.interfaces;

import ru.antonkuznetsov.graduationproject.model.Dish;

import java.util.List;

public interface DishService {

    Dish get(int id);

    List<Dish> getByMenuId(int menuId);

    Dish create(Dish dish, int menuId);

    Dish update(Dish dish, int menuId);

    void delete(int id);
}
