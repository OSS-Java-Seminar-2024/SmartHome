package com.fel.SmartHome.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fel.SmartHome.Model.enums.DeviceStatus;
import com.fel.SmartHome.Model.enums.DeviceType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType type;

    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Interface> interfaces = new ArrayList<>();

    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Logs> logs = new ArrayList<>();

}
