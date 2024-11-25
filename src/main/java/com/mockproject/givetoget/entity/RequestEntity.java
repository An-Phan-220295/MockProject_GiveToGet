package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "request")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String img;
    private boolean type;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String itemNames;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserInforEntity user;

    @OneToMany(mappedBy = "request")
    private List<TransactionEntity> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private StatusEntity status;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> item;
}
