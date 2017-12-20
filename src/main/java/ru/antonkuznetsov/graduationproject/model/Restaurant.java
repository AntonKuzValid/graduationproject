package ru.antonkuznetsov.graduationproject.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL, query = "SELECT r FROM Restaurant r left join fetch Dish "),
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {
    public static final String ALL = "getAll";

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Dish> menu) {
        super(id);
        this.name = name;
        this.menu = menu;
    }

    public Restaurant(String name, List<Dish> menu) {
        this.name = name;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

}
