package com.mockproject.givetoget.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "wards")
public class WardEntity {
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "full_name_en", length = 255)
    private String fullNameEn;

    @Column(name = "code_name", length = 255)
    private String codeName;

    @Column(name = "administrative_unit_id", length = 255)
    private int administrative_unit_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", nullable = false)
    private DistrictEntity district;


    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;
}
