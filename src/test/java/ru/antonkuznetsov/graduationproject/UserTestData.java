package ru.antonkuznetsov.graduationproject;

import ru.antonkuznetsov.graduationproject.model.Role;
import ru.antonkuznetsov.graduationproject.model.User;

public class UserTestData {
    public static final User USER_1 = new User(100002, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final int USER_1_ID = USER_1.getId();
    public static final User USER_2 = new User(100003, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final int USER_2_ID = USER_2.getId();
}
