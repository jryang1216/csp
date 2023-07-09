package com.ascending.demo.api.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExceptionResponse {

    private String message;
    private LocalDateTime dateTime;

    public ExceptionResponse(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
