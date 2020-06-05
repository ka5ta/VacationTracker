package com.example.VTracker.errorAPI;

import lombok.Data;

@Data
public class NoSuchEntityError {

    private String error = "No such object";
}
