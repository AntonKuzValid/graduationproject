package ru.antonkuznetsov.graduationproject.service.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote getByUser(int userId, LocalDate date);

    List<Vote> getAllByDate(LocalDate date);

    int getRating(int menuId);

    Vote vote(int menuId, int userId);
}
