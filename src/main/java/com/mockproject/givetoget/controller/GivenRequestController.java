package com.mockproject.givetoget.controller;

import com.mockproject.givetoget.response.BaseResponse;
import com.mockproject.givetoget.service.RequestService;
import com.mockproject.givetoget.utils.CodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GivenRequestController {
    @Autowired
    private RequestService requestService;
    @GetMapping("/v1/givenrequest")
    public ResponseEntity<?> getAllGivenRequest(){
        BaseResponse baseResponse = new BaseResponse().builder()
                .code(CodeMessage.SUCCESS.getCode())
                .message(CodeMessage.SUCCESS.getMessage())
                .data(requestService.findAllGivenRequest())
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
