package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false) // Người tạo bình luận
    private UserInforEntity user;

    @ManyToOne
    @JoinColumn(name = "id_partner", nullable = true) // Người liên quan
    private UserInforEntity partner;

    @ManyToOne
    @JoinColumn(name = "id_request", nullable = false)
    private RequestEntity request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private TransactionStatusEntity status;

//    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ItemEntity> itemEntities;
}
