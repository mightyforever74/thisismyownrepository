package com.project.schoolmanagment.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.schoolmanagment.entity.enums.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer absentee;//yoklama

    private Double midtermExam;

    private Double finalExam;

    private Double examAverage;

    private String infoNote;

    @ManyToOne//bircok ogr bir ogr olsun
    @JsonIgnoreProperties("teacher")
    private Teacher teacher;

    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private Note letterGrade;

    // lesson   EducationTerm

    @ManyToOne
    private Lesson lesson;

    @OneToOne
    private EducationTerm educationTerm;


}

