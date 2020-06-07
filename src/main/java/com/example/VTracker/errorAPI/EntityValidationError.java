package com.example.VTracker.errorAPI;

import lombok.Data;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class EntityValidationError {


    private Map<String, String> errorMessage;

    public EntityValidationError(ConstraintViolationException e) {
        errorMessage = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage()
                ));
    }
}
