package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant  r JOIN FETCH r.menu m WHERE r.id=?1")
    Restaurant getWithMenu(int id);
}
