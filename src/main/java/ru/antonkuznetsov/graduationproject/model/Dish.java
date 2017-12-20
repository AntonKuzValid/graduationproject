package ru.antonkuznetsov.graduationproject.model;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String name, int price, Restaurant restaurant) {
        super(id);
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }
}
