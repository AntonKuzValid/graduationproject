package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonkuznetsov.graduationproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
