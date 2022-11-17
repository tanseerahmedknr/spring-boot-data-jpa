package com.tanseerahmed.spring.data.jpa.tutorial.repository;

import com.tanseerahmed.spring.data.jpa.tutorial.entity.Course;
import com.tanseerahmed.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseDBA =
                Course.builder()
                        .title("DBA")
                        .credit(8)
                        .build();

        Course courseJava =
                Course.builder()
                        .title("Java")
                        .credit(8)
                        .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Mubassir")
                        .lastName("Asees")
//                        .courses(List.of(courseDBA,courseJava))
                        .build();

        teacherRepository.save(teacher);
    }

    @Test
    public void updateCreditByTitle(){
        teacherRepository.updateCredit(
                "10",
                "Java"
                );
    }
}