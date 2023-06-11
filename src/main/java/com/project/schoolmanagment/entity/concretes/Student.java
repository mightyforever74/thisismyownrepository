package com.project.schoolmanagment.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.schoolmanagment.entity.abstracts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
public class Student extends User {

    private String motherName;

    private String fatherName;

    private int studentNumber;

    private boolean isActive;
    @Column(unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)//ogrenci kaydedilirken rehber ogretmeni ike kaydedilmeli
    @JsonIgnore//surekli gitmesini engeller
    private AdvisoryTeacher advisorTeacher;

    // studentInfo, lessonProgram, meet
    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)// student silinince infosu da silinsin
    private List<StudentInfo> studentInfos;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "student_lessonprogram",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_program_id"))

    private Set<LessonProgram> lessonProgramList;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "meet_student_table",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "meet_id"))
    private List<Meet>meetList;



}