package com.mockproject.givetoget.utils.exceptionhandler;

import com.mockproject.givetoget.response.BaseResponse;
import com.mockproject.givetoget.utils.CodeMessage;
import com.mockproject.givetoget.utils.exception.NoDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<?> NoDataExceptionHandler(NoDataException e) {
        BaseResponse<Object> baseResponse = BaseResponse.builder()
                .code(CodeMessage.NO_DATA.getCode())
                .message(CodeMessage.NO_DATA.getMessage())
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
