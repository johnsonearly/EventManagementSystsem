package com.johnson.eventbookingsystem.dto;

import lombok.Data;

@Data
public class ResponseDTO<T>{
    private String message;
    private T data;
}
