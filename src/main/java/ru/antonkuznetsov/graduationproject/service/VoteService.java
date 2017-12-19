package ru.antonkuznetsov.graduationproject.service;

import ru.antonkuznetsov.graduationproject.model.Restaurant;

public interface VoteService {
    void vote(Restaurant restaurant, int userId);
}
