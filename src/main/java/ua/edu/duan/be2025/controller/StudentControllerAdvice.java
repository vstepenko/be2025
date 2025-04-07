package ua.edu.duan.be2025.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.edu.duan.be2025.dto.StudentErrorDto;
import ua.edu.duan.be2025.dto.TechnicalErrorDto;
import ua.edu.duan.be2025.dto.ValidationErrorDto;
import ua.edu.duan.be2025.service.StudentException;

@Slf4j
@ControllerAdvice
public class StudentControllerAdvice {


    @ExceptionHandler(StudentException.class)
    public ResponseEntity<StudentErrorDto> handleStudentError(StudentException studentException) {
        StudentErrorDto studentErrorDto = new StudentErrorDto();
        studentErrorDto.setErrorCode(studentException.getErrorCode().getCode());
        studentErrorDto.setErrorMessage(studentException.getErrorCode().getMessage());
        studentErrorDto.setErrorDateTime(studentException.getDateTime());
        return new ResponseEntity<>(studentErrorDto, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<TechnicalErrorDto> handleTechnicalError(Exception e) {

        log.error("Technical error", e);
        TechnicalErrorDto technicalErrorDto = new TechnicalErrorDto();
        technicalErrorDto.setMessage("Something going wrong");
        return new ResponseEntity<>(technicalErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorDto> handleConstraintViolation(ConstraintViolationException e) {
        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        validationErrorDto.setText(e.getMessage());

        return new ResponseEntity<>(validationErrorDto, HttpStatus.BAD_REQUEST);
    }
}
