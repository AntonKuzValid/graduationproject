package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> getByMenuRestaurantIdAndMenuDate(int restaurantId, LocalDate date);

    List<Dish> getByMenuId(int menuId);

    int deleteByMenuRestaurantIdAndMenuDate(int restaurantId, LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int deleteById(@Param("id") int dishId);
}
