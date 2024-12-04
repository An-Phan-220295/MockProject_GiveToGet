package com.mockproject.givetoget.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;

    @OneToOne(mappedBy = "account")
    private UserInforEntity userInfor;
}
