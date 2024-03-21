package com.example.interceptorCRUDStudent.servicies;

import com.example.interceptorCRUDStudent.entities.Student;
import com.example.interceptorCRUDStudent.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    public Optional<Student> createStudent(Student newStudent){
        Optional<List<Student>> studentListOpt = getByNameSurname(newStudent.getName(),newStudent.getSurname());
        if(studentListOpt.isPresent()){
            if(studentListOpt.get().isEmpty()){
                Student savedStudent = repository.save(newStudent);
                return Optional.of(savedStudent);
            }
        }
        return Optional.empty();
    }
    public List<Student> getAllStudents(){
        return repository.findAll();
    }
    public Optional<Student> getStudentFromId(Long id){
        return repository.findById(id);
    }
    public Optional<Student> updateStudenteNameSurname(Long id,Student studentEntity){
        Optional<Student> studentEntityOptional = repository.findById(id);
        if(studentEntityOptional.isPresent()){
            studentEntityOptional.get().setName(studentEntity.getName());
            studentEntityOptional.get().setSurname(studentEntity.getSurname());
            Student studentUpdated = repository.save(studentEntityOptional.get());
            return Optional.of(studentUpdated);
        }else {
            return Optional.empty();
        }
    }
    public Optional<Student> deleteStudenteFromId(Long id){
        Optional<Student> studentEntityOptional = repository.findById(id);
        if(studentEntityOptional.isPresent()){
            repository.delete(studentEntityOptional.get());
            return studentEntityOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<List<Student>> getByNameSurname(String name,String surname){
        Optional<List<Student>> studentEntityOptional = repository.findByNameSurname(name,surname);
        if(studentEntityOptional.isPresent()){
            return studentEntityOptional;
        }else {
            return Optional.empty();
        }
    }
}
