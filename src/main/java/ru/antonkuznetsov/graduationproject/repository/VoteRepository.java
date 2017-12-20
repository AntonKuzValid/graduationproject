package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Integer countByRestaurantIdAndVoteDate(Integer restaurantId, LocalDate voteDate);
}
