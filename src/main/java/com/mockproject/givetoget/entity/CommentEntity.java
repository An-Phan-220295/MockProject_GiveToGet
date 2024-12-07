package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "id_author", nullable = true)
    private UserInforEntity author;

    @ManyToOne
    @JoinColumn(name = "id_transaction", nullable = true)
    private TransactionEntity transaction;
}

