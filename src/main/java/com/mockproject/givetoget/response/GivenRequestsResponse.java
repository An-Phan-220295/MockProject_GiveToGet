package com.mockproject.givetoget.response;

import lombok.*;

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
    private String requestAddress;
    private String description;
    private String image;
    private String itemsName;
}
