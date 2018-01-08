package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> getByDate(LocalDate date);

    List<Menu> getByRestaurant(Restaurant restaurant);

    Optional<Menu> getByDateAndRestaurant(LocalDate date, Restaurant restaurant);

    int deleteById(int MenuId);
}
