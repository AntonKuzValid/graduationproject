package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> getByMenu(Menu menu);

    List<Vote> getByVoteDate(LocalDate date);

    Vote getByUserIdAndVoteDate(int userId, LocalDate date);

    Integer countByMenuAndVoteDate(Menu menu, LocalDate voteDate);

    int deleteById(int voteId);
}
