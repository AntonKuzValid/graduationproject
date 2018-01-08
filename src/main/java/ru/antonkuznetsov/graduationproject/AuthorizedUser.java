package ru.antonkuznetsov.graduationproject;


import ru.antonkuznetsov.graduationproject.model.AbstractBaseEntity;

public class AuthorizedUser {
    private static int id = AbstractBaseEntity.START_SEQ+2;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}