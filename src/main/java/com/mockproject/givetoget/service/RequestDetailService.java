package com.mockproject.givetoget.service;

import com.mockproject.givetoget.response.RequestDetailResponse;

public interface RequestDetailService {
    RequestDetailResponse findRequestDetailById(int id);
}
