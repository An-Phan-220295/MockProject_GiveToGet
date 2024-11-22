package com.mockproject.givetoget.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;

    @OneToOne(mappedBy = "account")
    private UserInforEntity userInfor;
}
