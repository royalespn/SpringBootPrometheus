package com.iman.springbootprometheus.repos;

import com.iman.springbootprometheus.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends CrudRepository<Student, Long> {
}
