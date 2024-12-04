package com.mockproject.givetoget.service.impl;

import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.response.DataResponse;
import com.mockproject.givetoget.repository.RequestRepository;
import com.mockproject.givetoget.response.GivenRequestsResponse;
import com.mockproject.givetoget.service.RequestService;
import com.mockproject.givetoget.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DataResponse<List<GivenRequestsResponse>> findAllGivenRequest(int pageNumber, Optional<String> provinceCode
            , Optional<String> districtCode, Optional<String> wardCode, Optional<String> search) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<RequestEntity> requestEntities = requestRepository.findAllGivenRequest(true, "OPENING", pageable
                , provinceCode, districtCode, wardCode, search);

//        List<GivenRequestsResponse> givenRequestsResponses = new ArrayList<>();
//        for (RequestEntity data : requestEntities.getContent()) {
//
//            String content = data.getDescription();
//
//            GivenRequestsResponse givenRequestsResponse = GivenRequestsResponse.builder()
//                    .id(data.getId())
//                    .title(data.getTitle())
//                    .createDate(utils.formatDateTime(data.getCreateDate()))
//                    .userName(data.getUser().getUsername())
//                    .address(utils.convertAddressToString(data))
//                    .content(content.length() > 200 ? content.substring(0, 200) + " . . ." : content)
//                    .image(!data.getImageEntities().isEmpty() ? data.getImageEntities().get(0).getImageName() : null)
//                    .itemsName(data.getItemNames())
//                    .build();
//
//            givenRequestsResponses.add(givenRequestsResponse);
//        }

        List<GivenRequestsResponse> givenRequestsResponses = requestEntities.getContent().stream().map(data -> {
            // Use ModelMapper for base mapping
            GivenRequestsResponse response = modelMapper.map(data, GivenRequestsResponse.class);

            // Custom mapping
            response.setAddress(utils.convertAddressToString(data));
            response.setCreateDate(utils.formatDateTime(data.getCreateDate()));
            response.setImage(data.getImageEntities() != null && !data.getImageEntities().isEmpty()
                    ? data.getImageEntities().get(0).getImageName()
                    : null);
            response.setItemsName(data.getItemNames());

            return response;
        }).collect(Collectors.toList());
        return new DataResponse<>(requestEntities.getTotalPages(), givenRequestsResponses);
    }
}
