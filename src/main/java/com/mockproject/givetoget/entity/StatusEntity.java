package com.mockproject.givetoget.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    private int idStatus;
    private String status;

}
