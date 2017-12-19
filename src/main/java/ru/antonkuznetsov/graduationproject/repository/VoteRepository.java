package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findAllByRestaurantAndVoteDate(Restaurant restaurant, LocalDate voteDate);

    Integer countVoteByRestaurantAndVoteDate(Restaurant restaurant, LocalDate voteDate);
}
