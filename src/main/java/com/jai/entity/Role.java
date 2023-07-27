package com.jai.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "ROLE_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    private String role;
}
