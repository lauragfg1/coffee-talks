package com.example.germanTalks.model;

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
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Size(max = 45)
    @NotNull
    @Column(name = "mail", nullable = false, length = 45)
    private String mail;

    @NotNull
    @Column(name = "participation", nullable = false)
    private Byte participation;

    @NotNull
    @Lob
    @Column(name = "level_german", nullable = false)
    private String levelGerman;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mother_tongue", nullable = false)
    private Language motherTongue;

    @OneToMany(mappedBy = "user1")
    private Set<Talk> talks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user2")
    private Set<Talk> talks2 = new LinkedHashSet<>();

    public Set<Talk> getTalks() {
        return talks;
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