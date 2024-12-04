package com.mockproject.givetoget.controller;

import com.mockproject.givetoget.response.BaseResponse;
import com.mockproject.givetoget.service.RequestDetailService;
import com.mockproject.givetoget.utils.CodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DetailRequestController {
    @Autowired
    private RequestDetailService requestDetailService;
    @GetMapping("/v1/givenrequest/detail")
    public ResponseEntity<?> getAllGivenRequest(@RequestParam int id) {

        BaseResponse baseResponse = new BaseResponse().builder()
                .code(CodeMessage.SUCCESS.getCode())
                .message(CodeMessage.SUCCESS.getMessage())
                .data(requestDetailService.findRequestDetailById(id))
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
