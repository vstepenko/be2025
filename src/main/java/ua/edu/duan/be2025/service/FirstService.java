package ua.edu.duan.be2025.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.edu.duan.be2025.dto.StudentDto;
import ua.edu.duan.be2025.entity.StudentEntity;
import ua.edu.duan.be2025.repository.StudentRepository;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;



@Slf4j
@Service
@RequiredArgsConstructor
public class FirstService {

    private final StudentRepository studentRepository;

    public StudentDto getStudent(String id) {
        log.info("Get student by id: {}  and another parameter {}", id, "param2");
        return studentRepository.findById(id)
                .map(this::convert)
                .orElseGet(
                        () -> {
                            log.info("Get student by id: {} failed", id);
                            throw new RuntimeException("Student not found");
                        }
                );
    }

    public List<StudentDto> getStudentByName(String name) {
        return studentRepository.findByFirstName(name)
                .stream()
                .map(this::convert)
                .toList();
    }

    public void addStudent(StudentDto studentDto) {
        StudentEntity studentEntity = convert(studentDto);
        studentEntity.setId(UUID.randomUUID().toString());
        studentRepository.save(studentEntity);
    }

    @Transactional
    public void editStudent(String id, StudentDto studentDto) {


        studentRepository.findById(id)
                .ifPresentOrElse(
                        student -> assemble(student, studentDto),
                        () -> {
                            throw new RuntimeException("Student not found");

                        });
    }

    public void deleteStudent(String id) {
        studentRepository.findById(id).ifPresentOrElse(
                studentRepository::delete,
                () -> {throw new RuntimeException("Student not found");}
        );
    }

    private StudentEntity convert(StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        assemble(studentEntity, studentDto);
        return studentEntity;
    }

    private void assemble( StudentEntity studentEntity, StudentDto studentDto){
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setMiddleName(studentDto.getMiddleName());
        studentEntity.setLastName(studentDto.getLastName());
    }
    private StudentDto convert(StudentEntity studentEntity) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentEntity.getId());
        studentDto.setFirstName(studentEntity.getFirstName());
        studentDto.setMiddleName(studentEntity.getMiddleName());
        studentDto.setLastName(studentEntity.getLastName());
        return studentDto;
    }
}
