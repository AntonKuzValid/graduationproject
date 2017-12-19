package ru.antonkuznetsov.graduationproject.to;

import ru.antonkuznetsov.graduationproject.model.Dish;
import ru.antonkuznetsov.graduationproject.model.Restaurant;
import ru.antonkuznetsov.graduationproject.model.Vote;

import java.util.List;
import java.util.Set;

public class RestaurantWithScore {
    private Integer id;
    private String name;
    private Integer score;
    private List<Dish> lunchMenu;
    private Set<Vote> votes;

    public RestaurantWithScore(Integer id, String name, Integer score, List<Dish> lunchMenu, Set<Vote> votes) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.lunchMenu = lunchMenu;
        this.votes = votes;

    }

    public RestaurantWithScore(Restaurant restaurant, int score) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.score = score;
        this.lunchMenu = restaurant.getMenu();
        this.votes = restaurant.getVotes();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Dish> getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(List<Dish> lunchMenu) {
        this.lunchMenu = lunchMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
