package com.tanseerahmed.spring.data.jpa.tutorial.repository;

import com.tanseerahmed.spring.data.jpa.tutorial.entity.Guardian;
import com.tanseerahmed.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){

        Guardian guardian =
                Guardian.builder()
                        .name("Zakir")
                        .mobile("1234567891")
                        .email("zakir.@gmail.com")
                        .build();

        Student student = Student.builder()
                .emailId("tanseerahmedknr@gmail.com")
                .firstName("Tanseer")
                .lastName("Ahmed")
                .guardian(guardian)

//                 This works when you integrate the class within the same entity without creating guardian class

//                .guardianName("Zakir")
//                .guardianMobile("1234567891")
//                .guardianEmail("zakir.@gmail.com")
//
                .build();

        studentRepository.save(student);
    }

//    Creating a guardian class and calling it with the method

    @Test
    public void saveStudentWithGuardian(){


        Guardian guardian = Guardian.builder()
                .name("Mirza")
                .email("mirza@gmail.com")
                .mobile("1234567891")
                .build();

        Student student = Student.builder()
                .firstName("Syed")
                .lastName("Saquib")
                .emailId("saquib@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList =
                studentRepository.findAll();
        System.out.println("studentList = "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList =
                studentRepository.findByFirstName("Tanseer");
        System.out.println("studentList = "+studentList);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> studentList =
                studentRepository.findByFirstNameContaining("ed");
        System.out.println("studentList = "+studentList);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> studentList =
                studentRepository.findByGuardianName("Mirza");

        System.out.println("studentList = "+studentList);
    }

//  Test method not working correctly

    @Test
    public void printStudentByLastnameAndFirstname(){
        List<Student> student =
                studentRepository.findByName(
                        "Ahmed",
                        "Syed"
                );
        System.out.println("student = "+student);
    }

    @Test
    public void getStudentByEmailAddress(){
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "tanseerahmedknr@gmail.com"
                );
        System.out.println("student = "+student);
    }

    @Test
    public void getStudentFirstNameByEmailAddress(){
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress(
                        "saquib@gmail.com"
                );
        System.out.println("firstName = "+firstName);
    }

    @Test
    public void getStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "saquib@gmail.com"
                );
        System.out.println("student = "+student);
    }

    @Test
    public void getStudentByEmailAddressNativeNamedParam(){
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam(
                        "tanseerahmedknr@gmail.com"
                );
        System.out.println("student = "+student);
    }

    @Test
    public void updateStudentByEmailIdTest(){

//        Method to update firstName using email Address

//        studentRepository.updateStudentByEmailId(
//                "Tanseer Ahmed",
//                "tanseerahmedknr@gmail.com");

//        Method to update guardian name using email address
                studentRepository.updateStudentByEmailId("Misbah","tanseerahmedknr@gmail.com");
    }

}