package ua.edu.duan.be2025.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ua.edu.duan.be2025.entity.StudentEntity;
import ua.edu.duan.be2025.repository.StudentRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FirstService {
    private final StudentRepository studentRepository;
    public String getStudent(String id) {
        return studentRepository.findById(id)
                .map(StudentEntity::getFirstName)
                .orElse("NOT FOUND");
    }

    public List<String> getStudentByName(String name) {
        return studentRepository.findByFirstName(name)
                .stream()
                .map(StudentEntity::getLastName)
                .toList();
    }
}
