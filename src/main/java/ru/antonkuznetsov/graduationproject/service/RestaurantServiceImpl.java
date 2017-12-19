package ru.antonkuznetsov.graduationproject.service;

import org.springframework.stereotype.Service;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.repository.RestaurantRepository;
import ru.antonkuznetsov.graduationproject.repository.VoteRepository;
import ru.antonkuznetsov.graduationproject.to.RestaurantWithScore;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    private VoteRepository voteRepository;

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
    public RestaurantWithScore get(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Restaurant with id=%s is not found", id)));
//        List<Vote> votes = voteRepository.findAllByRestaurantAndVoteDate(restaurant, LocalDate.now());
//        return new RestaurantWithScore(restaurant, votes.size());
        return new RestaurantWithScore(restaurant, voteRepository.countVoteByRestaurantAndVoteDate(restaurant, LocalDate.now()));
    }

    @Override
    public List<RestaurantWithScore> getAll() {
        return restaurantRepository.findAll().stream()
                .map(r -> new RestaurantWithScore(r, voteRepository.findAllByRestaurantAndVoteDate(r, LocalDate.now()).size()))
                .collect(Collectors.toList());
    }
}
