package ru.antonkuznetsov.graduationproject.to;

import ru.antonkuznetsov.graduationproject.model.Restaurant;

import java.util.Objects;

public class RestaurantWithRating {
    private Integer id;
    private String name;
    private Integer rating;

    public RestaurantWithRating(Integer id, String name, Integer rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public RestaurantWithRating(Restaurant restaurant, int rating) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantWithRating that = (RestaurantWithRating) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name)&&
                Objects.equals(rating, that.rating);
    }

    @Override
    public String toString() {
        return "RestaurantWithRating{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
