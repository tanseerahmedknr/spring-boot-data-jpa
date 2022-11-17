package com.tanseerahmed.spring.data.jpa.tutorial.repository;

import com.tanseerahmed.spring.data.jpa.tutorial.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    @Modifying
    @Transactional
    @Query(
          value = "update course set credit = ?1 where title =?2",
          nativeQuery = true
    )
    int updateCredit(String credit, String title);
}
