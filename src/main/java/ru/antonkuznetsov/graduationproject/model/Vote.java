package ru.antonkuznetsov.graduationproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}, name = "vote_unique_date_user_idx")})
public class Vote extends AbstractBaseEntity {
    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate voteDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    public Vote() {
    }

    public Vote(LocalDate voteDate, User user, Menu menu) {
        this.voteDate = voteDate;
        this.user = user;
        this.menu = menu;
    }

    public Vote(Integer id, LocalDate voteDate, User user, Menu menu) {
        super(id);
        this.voteDate = voteDate;
        this.user = user;
        this.menu = menu;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
