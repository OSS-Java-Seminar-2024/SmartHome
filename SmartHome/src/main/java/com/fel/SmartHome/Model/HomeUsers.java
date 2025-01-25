package com.fel.SmartHome.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@IdClass(HomeUsersId.class)
public class HomeUsers {
    @Id
    private Long homeid;

    @Id
    private Long userId;
}
