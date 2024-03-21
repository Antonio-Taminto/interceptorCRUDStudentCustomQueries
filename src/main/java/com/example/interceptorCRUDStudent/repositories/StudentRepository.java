package com.example.interceptorCRUDStudent.repositories;

import com.example.interceptorCRUDStudent.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE s.name = ?1 AND s.surname= ?2")
    Optional<List<Student>> findByNameSurname(String name, String surname);
}
