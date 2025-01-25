package com.fel.SmartHome.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String rooms;

    @OneToMany(mappedBy = "home",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Room> room = new ArrayList<>();

    @OneToMany(mappedBy = "home",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Schedule> schedules = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "HomeUsers",
            joinColumns = @JoinColumn(name = "home_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> users = new ArrayList<>();
}
