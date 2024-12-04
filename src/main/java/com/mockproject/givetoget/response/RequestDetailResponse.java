package com.mockproject.givetoget.response;

import com.mockproject.givetoget.entity.ImageEntity;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDetailResponse {
    private int id;
    private String title;
    private String createDate;
    private String userName;
    private String address;
    private String description;
    private List<ImageEntity> images;
    private Map<String, Integer> items;
}
