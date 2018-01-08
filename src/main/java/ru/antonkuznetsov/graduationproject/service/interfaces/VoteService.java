package ru.antonkuznetsov.graduationproject.service.interfaces;

import ru.antonkuznetsov.graduationproject.model.Vote;

import java.util.List;

public interface VoteService {

    List<Vote> getAll();

    Vote vote(int menuId, int userId);
}
