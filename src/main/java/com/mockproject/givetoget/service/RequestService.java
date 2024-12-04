package com.mockproject.givetoget.service;

import com.mockproject.givetoget.response.DataResponse;

import java.util.Optional;

public interface RequestService {
    DataResponse findAllGivenRequest(int pageNumber
            , Optional<String> provinceCode, Optional<String> districtCode
            , Optional<String> wardCode, Optional<String> search);
}
