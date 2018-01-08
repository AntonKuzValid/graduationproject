package ru.antonkuznetsov.graduationproject.service.interfaces;

import ru.antonkuznetsov.graduationproject.model.Menu;

public interface MenuService {
    Menu create(Menu menu);

    Menu update(Menu menu);
}
