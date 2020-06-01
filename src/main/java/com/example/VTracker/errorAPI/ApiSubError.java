package com.example.VTracker.errorAPI;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiSubError(String object, String message){
    this.object=object;
    this.message=message;
    }
}


