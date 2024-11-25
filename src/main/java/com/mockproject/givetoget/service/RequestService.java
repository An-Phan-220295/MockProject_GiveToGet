package com.mockproject.givetoget.service;

import com.mockproject.givetoget.response.GivenRequestsResponse;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    List<GivenRequestsResponse> findAllGivenRequest(int pageNumber
            , Optional<String> provinceCode, Optional<String> districtCode
            , Optional<String> wardCode, Optional<String> search);
}
