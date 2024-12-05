package com.mockproject.givetoget.request;


import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ItemRequest {
    private String itemName;
    private int quantities;
}
