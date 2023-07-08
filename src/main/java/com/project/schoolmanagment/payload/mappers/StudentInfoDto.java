package com.project.schoolmanagment.payload.mappers;

import com.project.schoolmanagment.entity.concretes.StudentInfo;
import com.project.schoolmanagment.entity.enums.Note;
import com.project.schoolmanagment.payload.request.StudentInfoRequest;
import com.project.schoolmanagment.payload.response.StudentInfoResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentInfoDto {

    @Autowired
    private StudentDto studentDto;

    public StudentInfo mapStudentInfoRequestToStudentInfo(StudentInfoRequest studentInfoRequest,
                                                          Note note,
                                                          Double average){
        return StudentInfo.builder()
                .infoNote(studentInfoRequest.getInfoNote())
                .absentee(studentInfoRequest.getAbsentee())
                .midtermExam(studentInfoRequest.getMidtermExam())
                .finalExam(studentInfoRequest.getFinalExam())
                .examAverage(average)
                .letterGrade(note)
                .build();
    }
    public StudentInfoResponse mapStudentInfoToStudentInfoResponse(StudentInfo studentInfo){
        return StudentInfoResponse.builder()
                .lessonName(studentInfo.getLesson().getLessonName())
                .creditScore(studentInfo.getLesson().getCreditScore())
                .isCompulsory(studentInfo.getLesson().getIsCompulsory())
                .educationTerm(studentInfo.getEducationTerm().getTerm())
                .id(studentInfo.getId())
                .absentee(studentInfo.getAbsentee())
                .midtermExam(studentInfo.getMidtermExam())
                .finalExam(studentInfo.getFinalExam())
                .infoNote(studentInfo.getInfoNote())
                .note(studentInfo.getLetterGrade())
                .average(studentInfo.getExamAverage())
                .studentResponse(studentDto.mapStudentToStudentResponse(studentInfo.getStudent()))
                .build();
    }

}
