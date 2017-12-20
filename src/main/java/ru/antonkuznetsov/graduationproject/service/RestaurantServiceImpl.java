package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.repository.RestaurantRepository;
import ru.antonkuznetsov.graduationproject.repository.VoteRepository;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithRating;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    private VoteRepository voteRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, VoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
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
    public void delete(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    @Override
    public RestaurantWithRating get(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Restaurant with id=%s is not found", id)));
        return new RestaurantWithRating(restaurant, voteRepository.countByRestaurantIdAndVoteDate(id, LocalDate.now()));
    }

    @Override
    public List<RestaurantWithRating> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(r -> new RestaurantWithRating(r, voteRepository.countByRestaurantIdAndVoteDate(r.getId(), LocalDate.now())))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantWithRating getRestaurantWithMenu(int id) {
        Restaurant restaurant = restaurantRepository.getWithMenu(id);
        return new RestaurantWithRating(restaurant, voteRepository.countByRestaurantIdAndVoteDate(id, LocalDate.now()));
    }
}
