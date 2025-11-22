package com.flightapp.exceptions;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flightapp.model.CityEnum;
import com.flightapp.model.MealType;
import com.flightapp.model.TripType;




@RestControllerAdvice
public class GlobalErrorHandler {

    String errorMessage="error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        for (ObjectError err : exception.getBindingResult().getAllErrors()) {
            String field = ((FieldError) err).getField();
            errorMap.put(field, err.getDefaultMessage());
        }
        return errorMap;
    }

    @ExceptionHandler(ValidationException.class)
    public Map<String, String> handleCustomValidation(ValidationException ex) {
        return Map.of(errorMessage, ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, String> handleNotFound(ResourceNotFoundException ex) {
        return Map.of(errorMessage, ex.getMessage());
    }

    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleInvalidJson(HttpMessageNotReadableException ex) {

        Map<String, String> error = new HashMap<>();
        Throwable cause = ex.getCause();

        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException invalidEx) {

            Class<?> targetType = invalidEx.getTargetType();

            if (targetType == CityEnum.class) {
                error.put(errorMessage, "Invalid city. Allowed values: " + Arrays.toString(CityEnum.values()));
                return error;
            }

            if (targetType == TripType.class) {
                error.put(errorMessage, "Invalid trip type. Allowed values: " + Arrays.toString(TripType.values()));
                return error;
            }

            if (targetType == MealType.class) {
                error.put(errorMessage, "Invalid meal type. Allowed values: " + Arrays.toString(MealType.values()));
                return error;
            }

            if (targetType == Boolean.class) {
                error.put(errorMessage, "Invalid value for mealAvailable. Allowed values: true or false");
                return error;
            }
        }

        if (cause instanceof DateTimeParseException) {
            error.put(errorMessage, "Invalid date format. Use yyyy-MM-dd");
            return error;
        }

        error.put(errorMessage, "Invalid JSON request");
        return error;
    }

   
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleOthers(Exception ex) {
        return Map.of(errorMessage, ex.getMessage());
    }
}

