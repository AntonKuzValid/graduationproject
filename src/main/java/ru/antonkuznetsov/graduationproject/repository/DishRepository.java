package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> getByMenuId(int menuId);

    int deleteById(int dishId);
}
