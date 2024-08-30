package com.example.germanTalks.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;


    @Size(max = 45)
    @NotNull
    @Column(name = "mail", nullable = false, length = 45)
    private String email;

    @Column(name = "participation")
    private Byte participation;


    @Lob
    @Column(name = "level_german")
    private String levelGerman;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_tongue")
    @JsonBackReference
    private Language motherTongue;

    @OneToMany(mappedBy = "user1")
    private Set<Talk> talks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user2")
    private Set<Talk> talks2 = new LinkedHashSet<>();

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Talk> getTalks() {
        return talks;
    }

    public void setEmail(@Size(max = 45) @NotNull String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLevelGerman(String levelGerman) {
        this.levelGerman = levelGerman;
    }

    public String getLevelGerman() {
        return levelGerman;
    }

    public void setParticipation(Byte participation) {
        this.participation = participation;
    }

    public Byte getParticipation() {
        return participation;
    }

    public void setTalks(Set<Talk> talks) {
        this.talks = talks;
    }

    public Language getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(Language motherTongue) {
        this.motherTongue = motherTongue;
    }

}