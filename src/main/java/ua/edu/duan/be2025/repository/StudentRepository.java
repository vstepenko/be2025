package ua.edu.duan.be2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.duan.be2025.entity.StudentEntity;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {
    List<StudentEntity> findByFirstName(String firstName);
}
