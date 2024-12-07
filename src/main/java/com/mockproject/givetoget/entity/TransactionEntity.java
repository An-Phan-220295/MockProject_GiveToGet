package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

//    @ManyToOne
//    @JoinColumn(name = "id_user", nullable = false) // Người tạo bình luận
//    private UserInforEntity user;

    @ManyToOne
    @JoinColumn(name = "id_partner", nullable = true) // Người liên quan
    private UserInforEntity partner;

    @ManyToOne
    @JoinColumn(name = "id_request", nullable = false)
    private RequestEntity request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private TransactionStatusEntity status;

    @OneToMany(mappedBy = "transaction")
    private List<CommentEntity> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_confirm", nullable = false)
    private TransactionConfirmStatusEntity userConfirmStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_confirm", nullable = false)
    private TransactionConfirmStatusEntity partnerConfirmStatus;
}
