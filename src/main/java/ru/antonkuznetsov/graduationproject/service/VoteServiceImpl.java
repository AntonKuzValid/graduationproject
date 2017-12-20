package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.User;
import ru.antonkuznetsov.graduationproject.model.Vote;
import ru.antonkuznetsov.graduationproject.repository.RestaurantRepository;
import ru.antonkuznetsov.graduationproject.repository.UserRepository;
import ru.antonkuznetsov.graduationproject.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VoteServiceImpl implements VoteService {
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(RestaurantRepository restaurantRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    @Transactional
    public void vote(int restaurantId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format("User with id %d is not found", userId)));
        Vote userVote = user.getVote();

        if (userVote != null && userVote.getVoteDate().isEqual(LocalDate.now())) {
            if (LocalTime.now().isAfter(LocalTime.of(11, 0))) return;
            userVote.setRestaurant(restaurantRepository.getOne(restaurantId));
        } else userVote = new Vote(LocalDate.now(), user, restaurantRepository.getOne(restaurantId));
        voteRepository.save(userVote);
    }
}
