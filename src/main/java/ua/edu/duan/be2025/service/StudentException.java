package ua.edu.duan.be2025.service;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class StudentException extends RuntimeException {

    private final ErrorCode errorCode;
    private final OffsetDateTime dateTime;

    public StudentException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.dateTime = OffsetDateTime.now();
    }
}
