package ua.edu.duan.be2025.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    STUDENT_NOT_FOUND("student_not_found", "Student has not been found"),
    NAME_TOO_LONG("name_too_long", "The name is too long");

    private  final String code;
    private final String message;
}
