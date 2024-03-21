package com.example.interceptorCRUDStudent.controllers;

import com.example.interceptorCRUDStudent.entities.Student;
import com.example.interceptorCRUDStudent.repositories.StudentRepository;
import com.example.interceptorCRUDStudent.servicies.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/post")
    public ResponseEntity<Student> postStudent(@RequestBody Student student){
        Optional<Student> studentOptional = service.createStudent(student);
        if(studentOptional.isPresent()){
            return ResponseEntity.ok().body(studentOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getStudentList(){
        return ResponseEntity.ok().body(service.getAllStudents());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentFromId(@PathVariable Long id){
        Optional<Student> studentOptional = service.getStudentFromId(id);
        if (studentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentOptional.get());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentNameAndSurname(
            @PathVariable Long id,@RequestBody Student student){
        Optional<Student> studentOptional = service.updateStudenteNameSurname(id,student);
        if(studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentOptional.get());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudentFromId(@PathVariable Long id){
        Optional<Student>studentOptional = service.deleteStudenteFromId(id);
        if(studentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(studentOptional.get());
    }
    @GetMapping("/get/bynamesurname")
    public ResponseEntity<List<Student>> getStudentFromNameAndSurname(@RequestParam String name,@RequestParam String surname){
        Optional<List<Student>> studentListOptional = service.getByNameSurname(name, surname);
        if(studentListOptional.isPresent()){
            return ResponseEntity.ok().body(studentListOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
