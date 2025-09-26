package com.yog.test.springdataweb.repository;

import com.yog.test.springdataweb.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "student-api", path = "student-api")
public interface StudentRepository extends JpaRepository<Student, Integer> {

    //JPA QUERY
    List<Student> findBySection(String section);
}