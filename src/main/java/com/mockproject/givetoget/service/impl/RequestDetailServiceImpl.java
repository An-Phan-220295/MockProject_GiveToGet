package com.mockproject.givetoget.service.impl;

import com.mockproject.givetoget.entity.ItemEntity;
import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.repository.RequestRepository;
import com.mockproject.givetoget.response.RequestDetailResponse;
import com.mockproject.givetoget.service.RequestDetailService;
import com.mockproject.givetoget.utils.Utils;
import com.mockproject.givetoget.utils.exception.NoDataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RequestDetailServiceImpl implements RequestDetailService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RequestDetailResponse findRequestDetailById(int id) {
        RequestEntity requestEntity = requestRepository.findByIdAndStatus_Status(id, "OPENING");
        //Check null
        if(requestEntity == null){
            throw new NoDataException("requestEntity is null");
        }

        //Map data
        RequestDetailResponse response = modelMapper.map(requestEntity, RequestDetailResponse.class);
        Map<String, Integer> items = new HashMap<>();
        for (ItemEntity data : requestEntity.getItem()) {
            items.put(data.getItemName(), data.getQuantity());
        }
        response.setImages(requestEntity.getImages());
        response.setItems(items);
        response.setCreateDate(utils.formatDateTime(requestEntity.getCreateDate()));
        response.setRequestAddress(utils.convertAddressToString(requestEntity));

        return response;
    }
}
