package com.mockproject.givetoget.service.impl;

import com.mockproject.givetoget.entity.ItemEntity;
import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.utils.exception.NoDataException;
import com.mockproject.givetoget.repository.RequestRepository;
import com.mockproject.givetoget.response.GivenRequestsResponse;
import com.mockproject.givetoget.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<GivenRequestsResponse> findAllGivenRequest(int pageNumber, Optional<String> provinceCode
            , Optional<String> districtCode, Optional<String> wardCode, Optional<String> search) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<RequestEntity> requestEntities = requestRepository.findAllGivenRequest(true, "OPENING", pageable
                , provinceCode, districtCode, wardCode, search);

        List<GivenRequestsResponse> givenRequestsResponses = new ArrayList<>();
        for (RequestEntity data : requestEntities.getContent()) {
            StringBuilder address = new StringBuilder();
            List<String> items = new ArrayList<>();

            address.append(data.getUser().getAddress().getStreet()).append(", ")
                    .append(data.getUser().getAddress().getWard().getFullName()).append(", ")
                    .append(data.getUser().getAddress().getWard().getDistrict().getFullName()).append(", ")
                    .append(data.getUser().getAddress().getWard().getDistrict().getProvince().getFullName()).append(".");
            for (ItemEntity item : data.getItem()) {
                items.add(item.getItemName());
                //Create an items in request
            }

            String content = data.getDescription();
            GivenRequestsResponse givenRequestsResponse = new GivenRequestsResponse().builder()
                    .id(data.getId())
                    .title(data.getTitle())
                    .createDate(DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy").format(data.getCreateDate()))
                    .userName(data.getUser().getUsername())
                    .address(address.toString())
                    .content(content.length() > 200 ? content.substring(0, 200) + " . . ." : content)
                    .image(data.getImg())
                    .itemsName(items)
                    .build();
            givenRequestsResponses.add(givenRequestsResponse);
        }
        return givenRequestsResponses;
    }
}
