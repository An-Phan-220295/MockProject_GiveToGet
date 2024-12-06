package com.mockproject.givetoget.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ward", nullable = false)
    private WardEntity ward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_userinfor", nullable = false)
    private UserInforEntity user;

    @OneToOne(mappedBy = "address")
    private RequestEntity request;

}
