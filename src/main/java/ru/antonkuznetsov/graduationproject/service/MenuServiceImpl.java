package ru.antonkuznetsov.graduationproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonkuznetsov.graduationproject.model.Menu;
import ru.antonkuznetsov.graduationproject.repository.MenuRepository;
import ru.antonkuznetsov.graduationproject.repository.RestaurantRepository;
import ru.antonkuznetsov.graduationproject.service.interfaces.MenuService;

import java.util.List;

import static java.time.LocalDate.now;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Menu get(int id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Menu with id %d is not found", id)));
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAllByRestaurantId(restaurantId);
    }

    @Override
    @Transactional
    public Menu create(Menu menu, int restaurantId) {
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return menuRepository.save(menu);
    }

    @Override
    public void delete(int id) {
        menuRepository.deleteById(id);
    }
}
