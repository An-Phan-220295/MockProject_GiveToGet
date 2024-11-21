package com.mockproject.givetoget.entity;


import com.mockproject.givetoget.entity.compositekey.HistoryKey;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "history")
public class HistoryEntity {
    @EmbeddedId
    private HistoryKey HistoryKey;
    private boolean status;
    private LocalDateTime acceptDateTime;
}
