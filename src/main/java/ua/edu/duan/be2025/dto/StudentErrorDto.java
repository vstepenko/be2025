package ua.edu.duan.be2025.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class StudentErrorDto {
    private String errorCode;
    private String errorMessage;
    private OffsetDateTime errorDateTime;
}
