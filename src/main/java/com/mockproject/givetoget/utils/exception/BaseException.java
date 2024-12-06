package com.mockproject.givetoget.utils.exception;

import com.mockproject.givetoget.utils.CodeMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException{
    private CodeMessage codeMessage;

    public BaseException(CodeMessage codeMessage) {
        super(codeMessage.getMessage());
        this.codeMessage = codeMessage;
    }
}
