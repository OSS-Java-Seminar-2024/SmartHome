package com.fel.SmartHome.Model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HomeUsersId implements Serializable {
    private Long homeId;
    private Long userId;
}
