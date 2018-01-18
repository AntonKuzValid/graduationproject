package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.model.User;
import ru.antonkuznetsov.graduationproject.model.Vote;
import ru.antonkuznetsov.graduationproject.repository.MenuRepository;
import ru.antonkuznetsov.graduationproject.repository.UserRepository;
import ru.antonkuznetsov.graduationproject.repository.VoteRepository;
import ru.antonkuznetsov.graduationproject.service.interfaces.VoteService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.time.LocalDate.now;

@Service
public class VoteServiceImpl implements VoteService {
    private MenuRepository menuRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(MenuRepository menuRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getByUser(int userId, LocalDate date) {
        return voteRepository.getWithMenu(userId, date);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.getAllByVoteDate(date);
    }

    @Override
    public int getRating(int menuId) {
        return voteRepository.countVotes(menuId);
    }

    @Override
    @Transactional
    public Vote vote(int menuId, int userId) {
//        1 query the database if user has voted today and it's after 11 already
        Vote vote = voteRepository.getByUserIdAndVoteDate(userId, now());
        boolean isVoteUpdatedToday = vote != null && vote.getVoteDate().isEqual(now());
        if (isVoteUpdatedToday) if (LocalTime.now().isAfter(LocalTime.of(11, 0))) return null;

//        3 query the database if user has voted today and it's before 11 yet
        Menu menu = menuRepository.getOne(menuId);
        if (isVoteUpdatedToday) vote.setMenu(menu);
        else {
//        4 query the database if user hasn't voted today
            User user = userRepository.getOne(userId);
            vote = new Vote(now(), user, menu);
        }
        return voteRepository.save(vote);
    }
}
