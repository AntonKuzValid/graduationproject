package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.repository.DishRepository;
import ru.antonkuznetsov.graduationproject.repository.MenuRepository;
import ru.antonkuznetsov.graduationproject.service.interfaces.DishService;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Dish get(int id) {
        return dishRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Dish with id %s is not found", id)));
    }

    @Cacheable("dishes")
    @Override
    public List<Dish> getByMenuId(int menuId) {
        return dishRepository.getByMenuId(menuId);
    }

    @CacheEvict(cacheNames = "dishes", allEntries = true)
    @Override
    @Transactional
    public Dish create(Dish dish, int menuId) {
        dish.setMenu(menuRepository.getOne(menuId));
        return dishRepository.save(dish);
    }

    @CacheEvict(cacheNames = "dishes", allEntries = true)
    @Override
    @Transactional
    public Dish update(Dish dish, int menuId) {
        dish.setMenu(menuRepository.getOne(menuId));
        return dishRepository.save(dish);
    }

    @CacheEvict(cacheNames = "dishes", allEntries = true)
    @Override
    public void delete(int id) {
        if (dishRepository.deleteById(id) == 0)
            throw new RuntimeException(String.format("Dish with id %s is not found", id));
    }
}
