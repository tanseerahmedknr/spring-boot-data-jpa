package com.tanseerahmed.spring.data.jpa.tutorial.repository;

import com.tanseerahmed.spring.data.jpa.tutorial.entity.Course;
import com.tanseerahmed.spring.data.jpa.tutorial.entity.Student;
import com.tanseerahmed.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Tim")
                .lastName("Southee")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(5)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void printAllCourse(){
        List<Course> course =
                courseRepository.findAll();
        System.out.println("Course List = " +course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getContent();

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalElements();

        long totalPages =

//     For (firstPageWithThreeRecords)  At this moment there are 5 records so the max limited to 3 record per page,
//     so it divides into 2 pages

//     If using the secondPageWithTwoRecords then pages will be divided into 3 pages co max limit is 2 per page

//                courseRepository.findAll(secondPageWithTwoRecords)

                courseRepository.findAll(firstPageWithThreeRecords)
                       .getTotalPages();

        System.out.println("total pages = "+totalPages);

        System.out.println("total elements = "+totalElements);

        System.out.println("course = "+courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );

        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit").descending()
                );

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title").descending()
                                .and(Sort.by("credit"))
                );

        List<Course> courses =
                courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = "+ courses);
    }

    @Test
    public void printFindTitleContaining(){
        Pageable firstTenRecords =
                PageRequest.of(
                        0,
                        10
                );

        List<Course> courses =
                courseRepository.findByTitleContaining(
                        "D",
                        firstTenRecords
                ).getContent();

        System.out.println("courses = "+ courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher =
                Teacher.builder()
                        .firstName("Alex")
                        .lastName("Hales")
                        .build();

        Student student =
                Student.builder()
                        .firstName("Tim")
                        .lastName("Henman")
                        .emailId("timhenman@gmail.com")
                        .build();

        Course course =
                Course.builder()
                        .title("AI")
                        .credit(12)
                        .teacher(teacher)
                        .build();

                course.addStudents(student);

                courseRepository.save(course) ;
    }
}