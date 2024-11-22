package com.mockproject.givetoget.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GivenRequestsResponse {
    private int id;
    private String title;
    private String createDate;
    private String userName;
    private String address;
    private String content;
    private String image;
    private List<String> itemsName;
}
