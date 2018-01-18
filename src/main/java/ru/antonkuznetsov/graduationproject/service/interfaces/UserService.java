package ru.antonkuznetsov.graduationproject.service.interfaces;

import ru.antonkuznetsov.graduationproject.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    void update(User user);

    List<User> getAll();

    void enable(int id, boolean enable);
}