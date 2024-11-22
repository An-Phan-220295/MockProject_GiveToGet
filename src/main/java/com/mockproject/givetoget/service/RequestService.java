package com.mockproject.givetoget.service;

import com.mockproject.givetoget.response.GivenRequestsResponse;

import java.util.List;

public interface RequestService {
    List<GivenRequestsResponse> findAllGivenRequest();
}
