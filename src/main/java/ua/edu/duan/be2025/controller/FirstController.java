package ua.edu.duan.be2025.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.duan.be2025.dto.StudentDto;
import ua.edu.duan.be2025.service.FirstService;

import java.util.List;


@RestController
public class FirstController {

    private FirstService firstService;

    @Autowired(required = false)
    public void setFirstService(FirstService firstService) {
        this.firstService = firstService;
    }

    @GetMapping(value = "/hello-world")
    public String helloWord(){
        return "Hello World!";

    }

    @GetMapping(value = "/student")
    public StudentDto getStudent(@RequestParam String id) {
        return firstService.getStudent(id);
    }

    @GetMapping(value = "/student-by-name")
    public List<StudentDto> getStudentByName(@RequestParam String name) {
        return firstService.getStudentByName(name);
    }

    @PostMapping(value = "/student")
    public void addStudent(@RequestBody StudentDto studentDto) {
      firstService.addStudent(studentDto);
    }

    @PutMapping(value = "/student")
    public void  editStudent(@RequestParam String id, @RequestBody StudentDto studentDto){
        firstService.editStudent(id, studentDto);
    }

    @DeleteMapping(value = "/student")
    public void deleteStudent(@RequestParam String id){
       firstService.deleteStudent(id);
    }

}
