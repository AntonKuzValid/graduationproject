package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.repository.RestaurantRepository;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) {
        if (restaurantRepository.deleteById(id) == 0)
            throw new RuntimeException(String.format("Restaurant with id=%s is not found", id));
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Restaurant with id=%s is not found", id)));
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }
}
