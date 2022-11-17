package com.tanseerahmed.spring.data.jpa.tutorial.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")

public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
//    @Column(
//            name = "course_material_id"
//    )
    private Long courseMaterialId;
    private String url;


    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "course_id",
//            This is foreign Key which is used as reference column from course entity
            referencedColumnName = "courseId"
    )
    private Course course;

}
