package com.project.schoolmanagment.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Meet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Lesson id;

    private Serializable description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")
    private LocalTime startTime;
/*
    public void main(String[] args) {
        Duration gap = Duration.ofSeconds(10);
        gap.
    }

   */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")
    private LocalTime stopTime;

    @ManyToOne(cascade = CascadeType.PERSIST)// birisi kaybedilirsedevam etsin
    @JsonIgnoreProperties("teacher")//all the needs will be done by the teacher
    private AdvisoryTeacher advisorTeacher;

    @ManyToMany(mappedBy = "meetList", fetch = FetchType.EAGER)//!!!
    @JoinTable(
            name = "meet_student_table",
            joinColumns = @JoinColumn(name = "meet_id"),
            inverseJoinColumns = @JoinColumn(name = "studebt_id")
    )
    private List<Student> studentList;


}
