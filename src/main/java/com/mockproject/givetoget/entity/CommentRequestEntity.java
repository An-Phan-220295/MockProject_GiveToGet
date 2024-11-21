package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "comment_request")
public class CommentRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false) // Người tạo bình luận
    private UserInforEntity user;

    @ManyToOne
    @JoinColumn(name = "id_partner", nullable = true) // Người liên quan
    private UserInforEntity partner;

    @ManyToOne
    @JoinColumn(name = "id_request", nullable = false)
    private RequestEntity request;
}

