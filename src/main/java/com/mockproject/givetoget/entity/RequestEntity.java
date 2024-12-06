package com.mockproject.givetoget.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "request")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private boolean type;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String itemNames;

    @OneToOne
    @JoinColumn(name = "id_address")
    private AddressEntity address;

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

    @OneToMany(mappedBy = "requestEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> imageEntities;
}
