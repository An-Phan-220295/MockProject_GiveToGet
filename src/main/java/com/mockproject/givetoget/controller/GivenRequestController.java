package com.mockproject.givetoget.controller;

import com.mockproject.givetoget.response.BaseResponse;
import com.mockproject.givetoget.response.DataResponse;
import com.mockproject.givetoget.service.RequestService;
import com.mockproject.givetoget.utils.CodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GivenRequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping("/v1/givenrequest")
    public ResponseEntity<?> getAllGivenRequest(@RequestParam(defaultValue = "0") int pageNumber,
                                                @RequestParam(required = false) String provinceCode,
                                                @RequestParam(required = false) String districtCode,
                                                @RequestParam(required = false) String wardCode,
                                                @RequestParam(required = false) String search) {
        System.out.println(pageNumber);
        BaseResponse baseResponse = new BaseResponse().builder()
                .code(CodeMessage.SUCCESS.getCode())
                .message(CodeMessage.SUCCESS.getMessage())
                .data(requestService.findAllGivenRequest(pageNumber, Optional.ofNullable(provinceCode)
                        , Optional.ofNullable(districtCode), Optional.ofNullable(wardCode), Optional.ofNullable(search)))
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
