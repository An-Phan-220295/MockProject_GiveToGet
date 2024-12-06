package com.mockproject.givetoget.utils;

import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.utils.exception.BaseException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class Utils {
    public String convertAddressToString(RequestEntity data) {
        StringBuilder address = new StringBuilder();

        address.append(data.getUser().getAddress().getStreet()).append(", ")
                .append(data.getUser().getAddress().getWard().getFullName()).append(", ")
                .append(data.getUser().getAddress().getWard().getDistrict().getFullName()).append(", ")
                .append(data.getUser().getAddress().getWard().getDistrict().getProvince().getFullName()).append(".");
        return address.toString();
    }

    public String formatDateTime(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy").format(localDateTime);
    }
}
