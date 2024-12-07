package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private UserInforEntity users;

    @ManyToOne
    @JoinColumn(name = "id_partner", nullable = true) // Người liên quan
    private UserInforEntity partner;

    @ManyToOne
    @JoinColumn(name = "id_request", nullable = true) // Người liên quan
    private RequestEntity request;
}

