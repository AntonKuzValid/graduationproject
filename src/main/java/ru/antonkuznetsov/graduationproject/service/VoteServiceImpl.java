package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.model.User;
import ru.antonkuznetsov.graduationproject.model.Vote;
import ru.antonkuznetsov.graduationproject.repository.UserRepository;
import ru.antonkuznetsov.graduationproject.repository.VoteRepository;

import java.time.LocalDate;

public class VoteServiceImpl implements VoteService {
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(UserRepository userRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    @Transactional
    public void vote(Restaurant restaurant, int userId) {
        User user = userRepository.findById(userId).get();
        Vote userVote = user.getVote();
        if (userVote != null && userVote.getVoteDate().isEqual(LocalDate.now())) {
            userVote.setRestaurant(restaurant);
        } else userVote = new Vote(LocalDate.now(), user, restaurant);
        voteRepository.save(userVote);
    }
}
