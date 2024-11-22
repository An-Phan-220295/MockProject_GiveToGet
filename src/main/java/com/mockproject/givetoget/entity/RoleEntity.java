package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    private String name;
    private String description;

    @OneToMany(mappedBy = "role")
    private List<AccountEntity> accounts;
}
