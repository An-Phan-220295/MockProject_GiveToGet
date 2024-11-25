package com.mockproject.givetoget.service.impl;

import com.mockproject.givetoget.entity.ItemEntity;
import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.response.DataResponse;
import com.mockproject.givetoget.repository.RequestRepository;
import com.mockproject.givetoget.response.GivenRequestsResponse;
import com.mockproject.givetoget.service.RequestService;
import com.mockproject.givetoget.utils.Utils;
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
    @Autowired
    private Utils utils;
    @Override
    public DataResponse findAllGivenRequest(int pageNumber, Optional<String> provinceCode
            , Optional<String> districtCode, Optional<String> wardCode, Optional<String> search) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<RequestEntity> requestEntities = requestRepository.findAllGivenRequest(true, "OPENING", pageable
                , provinceCode, districtCode, wardCode, search);

        List<GivenRequestsResponse> givenRequestsResponses = new ArrayList<>();
        for (RequestEntity data : requestEntities.getContent()) {

            String content = data.getDescription();
            GivenRequestsResponse givenRequestsResponse = new GivenRequestsResponse().builder()
                    .id(data.getId())
                    .title(data.getTitle())
                    .createDate(DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy").format(data.getCreateDate()))
                    .userName(data.getUser().getUsername())
                    .address(utils.convertAddressToString(data))
                    .content(content.length() > 200 ? content.substring(0, 200) + " . . ." : content)
                    .image(data.getImg())
                    .itemsName(data.getItemNames())
                    .build();
            givenRequestsResponses.add(givenRequestsResponse);
        }
        return new DataResponse(requestEntities.getTotalPages(),givenRequestsResponses);
    }
}
