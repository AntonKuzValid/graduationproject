package ru.antonkuznetsov.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.User;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);

    int deleteById(int userId);
}
