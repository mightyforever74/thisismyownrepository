package com.project.schoolmanagment.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    private String lessonName;

    private Integer creditScore;
    /*
    nicin int degil de Integer wrapperclass kullandik
    avantaj 1 default degeri olmasin credit scor u dusunup gimemeiz gerekir yoksa null gelir
    avantaj 2 wrapper class larin arkasinda metodlar var biraz daha erisimi kolay

     */
    private  Boolean isCompulsory;// zorulu derslerde birimi


}
