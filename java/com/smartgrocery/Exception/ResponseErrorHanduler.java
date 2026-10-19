package com.smartgrocery.Exception;

import java.time.LocalDateTime;
import java.util.Map;

//import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_NULL)   // hide fields that are null
public class ResponseErrorHanduler {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final Map<String, String> validationErrors;

    public ResponseErrorHanduler(int status, String error, String message, String path, Map<String, String> validationErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.validationErrors = validationErrors;
    }

    public LocalDateTime getTimestamp(){
    	return timestamp; 
    }
    public int getStatus(){
    	return status;
    }
    public String getError(){
    	return error;
    }
    public String getMessage(){
    	return message;
    }
    public String getPath(){
    	return path;
    }
    public Map<String, String> getValidationErrors(){
    	return validationErrors;
    }
}
