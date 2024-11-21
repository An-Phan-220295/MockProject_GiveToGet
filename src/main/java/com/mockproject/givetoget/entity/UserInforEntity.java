package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user_infor")
public class UserInforEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private Date dateOfBirth;
    private String gender;
    private String email;
    private String phone;
    private LocalDate createDate;
    private String avatar;
    private int points;

    @OneToOne
    @JoinColumn(name = "id_account")
    private AccountEntity account;


    @OneToMany(mappedBy = "user")
    private List<RequestEntity> requests;

    @OneToOne
    @JoinColumn(name = "id_address", nullable = true)
    private AddressEntity address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Bình luận do người dùng tạo
    private List<TransactionEntity> transaction;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL) // Bình luận nhắc đến người dùng này
    private List<TransactionEntity> partnerTransaction;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentRequestEntity> commentRequest;
}
