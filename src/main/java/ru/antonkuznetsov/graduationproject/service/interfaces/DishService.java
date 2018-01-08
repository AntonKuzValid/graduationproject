package ru.antonkuznetsov.graduationproject.service.interfaces;

import ru.antonkuznetsov.graduationproject.model.Dish;

public interface DishService {
    Dish create(Dish dish);

    Dish update(Dish dish);
}
