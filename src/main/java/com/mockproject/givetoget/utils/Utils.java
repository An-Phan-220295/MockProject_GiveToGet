package com.mockproject.givetoget.utils;

import com.mockproject.givetoget.entity.RequestEntity;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public String convertAddressToString(RequestEntity data){
        StringBuilder address = new StringBuilder();

        address.append(data.getUser().getAddress().getStreet()).append(", ")
                .append(data.getUser().getAddress().getWard().getFullName()).append(", ")
                .append(data.getUser().getAddress().getWard().getDistrict().getFullName()).append(", ")
                .append(data.getUser().getAddress().getWard().getDistrict().getProvince().getFullName()).append(".");
        return address.toString();
    }
}
