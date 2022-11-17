package com.tanseerahmed.spring.data.jpa.tutorial.repository;

import com.tanseerahmed.spring.data.jpa.tutorial.entity.Guardian;
import com.tanseerahmed.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining (String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

//    Not Working properly
    @Query(
            "select s from Student s where (:lastName is null or s.lastName = :lastName) "
                    + "and (:firstName is null or s.firstName = :firstName)" )
    List<Student> findByName(
            @Param("lastName") String lastName,
            @Param("firstName") String firstName);

//    JPQL Query
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);



//    JPQL Query
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);


//    Native Query
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address =?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);


//    Native Query Param
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailId") String emailId
    );


// Modifying and Transactional Query (for updating and the data @Modifying is used
// @Transactional is for maintaining the transactions)

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set guardian_name = ?1 where email_address =?2",
            nativeQuery = true
    )
    int updateStudentByEmailId(String name , String emailId);

}
