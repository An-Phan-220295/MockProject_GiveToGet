package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idUser;
    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_province", nullable = false)
    private ProvincesEntity province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district", nullable = false)
    private DistrictEntity district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ward", nullable = false)
    private WardEntity ward;

    @OneToOne(mappedBy = "address")
    private UserInforEntity user;
}
