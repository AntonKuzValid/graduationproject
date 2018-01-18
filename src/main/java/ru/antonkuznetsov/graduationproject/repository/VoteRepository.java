package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote  v JOIN FETCH v.menu where v.user.id=:id AND v.voteDate=:date")
    Vote getWithMenu(@Param("id") int userId, @Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:id AND v.voteDate=:date")
    Vote getByUserIdAndVoteDate(@Param("id") int userId, @Param("date") LocalDate date);

    List<Vote> getAllByVoteDate(LocalDate date);

    @Query("SELECT COUNT (v) FROM Vote v WHERE v.menu.id=:id")
    Integer countVotes(@Param("id") int menuId);

    int deleteById(int voteId);
}
