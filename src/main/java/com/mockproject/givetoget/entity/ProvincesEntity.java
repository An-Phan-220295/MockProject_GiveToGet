package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "provinces")
public class ProvincesEntity {

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

    @Column(name = "administrative_region_id", length = 255)
    private int administrative_region_id;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DistrictEntity> districts;

}
