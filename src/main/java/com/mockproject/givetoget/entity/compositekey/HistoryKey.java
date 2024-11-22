package com.mockproject.givetoget.entity.compositekey;

import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.entity.UserInforEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class HistoryKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_request")
    private RequestEntity request;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserInforEntity user;
}
