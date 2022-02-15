package ua.yarynych.myoauth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.yarynych.myoauth.exeptions.Exceptions;

public class ErrorResponseDto {

    private Exceptions exception;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String exception_description;


    public ErrorResponseDto() {
    }

    public ErrorResponseDto(Exceptions exception, String exception_description) {
        this.exception = exception;
        this.exception_description = exception_description;
    }


    public Exceptions getException() {
        return exception;
    }

    public void setException(Exceptions exception) {
        this.exception = exception;
    }

    @JsonProperty("exception_description")
    public String getException_description() {
        return exception_description;
    }

    public void setException_description(String exception_description) {
        this.exception_description = exception_description;
    }
}
