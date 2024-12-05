package com.mockproject.givetoget.request;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequestData {
    private String title;
    private String description;
    private List<ItemRequest> item;
}
