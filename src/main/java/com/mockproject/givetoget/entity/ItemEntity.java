package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String itemName;
    private String itemDescription;
    private int quantity;
    private int currentQuantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_request")
    private RequestEntity request;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;
}
