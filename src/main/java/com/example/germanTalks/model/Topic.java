package com.example.germanTalks.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topic", nullable = false, unique = true)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 25)
    @Column(name = "german_name", nullable = false, length = 25)
    private String german_name;

    @Size(max = 200)
    @Column(name = "video", length = 200)
    private String video;

    @OneToMany(mappedBy = "topic")
    @JsonManagedReference
    private Set<Question> questions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "topic")
    private Set<Talk> talks = new LinkedHashSet<>();

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Talk> getTalks() {
        return talks;
    }

    public void setTalks(Set<Talk> talks) {
        this.talks = talks;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGerman_name() {
        return german_name;
    }

    public void setGerman_name(String name) {
        this.german_name = name;
    }
}
