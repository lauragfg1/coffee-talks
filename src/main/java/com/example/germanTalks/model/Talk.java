package com.example.germanTalks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.Instant;

@Entity
@Table(name = "talk")
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talk", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user1", nullable = false)
    private User user1;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user2", nullable = false)
    private User user2;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic", nullable = false)
    private Topic topic;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "completed", nullable = false)
    private byte completed;

    public Talk(Instant date, OidcUser user1, User user2, Topic topic) {
        this.user1 = (User) user1;
        this.user2 = user2;
        this.topic = topic;
        this.date = date;
        this.completed =  (byte) 0;
    }

    public Talk() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte getCompleted() {
        return completed;
    }

    public void setCompleted(byte completed) {
        this.completed = completed;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }
}
