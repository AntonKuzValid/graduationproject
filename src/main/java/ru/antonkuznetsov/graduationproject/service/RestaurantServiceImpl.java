package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.repository.MenuRepository;
import ru.antonkuznetsov.graduationproject.repository.RestaurantRepository;
import ru.antonkuznetsov.graduationproject.repository.VoteRepository;
import ru.antonkuznetsov.graduationproject.service.interfaces.RestaurantService;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    private VoteRepository voteRepository;
    private MenuRepository menuRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, VoteRepository voteRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.menuRepository = menuRepository;
    }


    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        if (restaurantRepository.deleteById(id) == 0)
            throw new RuntimeException(String.format("Restaurant with id=%s is not found", id));
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Restaurant with id=%s is not found", id)));
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public RestaurantWithRating getWithRating(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Restaurant with id=%s is not found", id)));
        Menu menu = menuRepository.getByDateAndRestaurant(now(), restaurant).orElseThrow(() ->
                new RuntimeException(String.format("Menu of restaurant  %s for date %s is not found", now().toString(), restaurant.getName())));
        return new RestaurantWithRating(restaurant, voteRepository.countByMenuAndVoteDate(menu, now()));
    }

    @Override
    public List<RestaurantWithRating> getAllWithRating() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> new RestaurantWithRating(restaurant,
                        voteRepository.countByMenuAndVoteDate(menuRepository.getByDateAndRestaurant(now(), restaurant).orElseThrow(() ->
                                new RuntimeException(String.format("Menu of restaurant  %s for date %s is not found", now().toString(), restaurant.getName()))), now())))
                .collect(Collectors.toList());
    }
}
