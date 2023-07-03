package com.project.schoolmanagment.payload.mappers;

import com.project.schoolmanagment.entity.concretes.Teacher;
import com.project.schoolmanagment.payload.request.TeacherRequest;
import com.project.schoolmanagment.payload.response.TeacherResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TeacherDto {

    public Teacher mapTeacherRequestToteacher(TeacherRequest teacherRequest){
        return Teacher.builder()
			.name(teacherRequest.getName())
            .surname(teacherRequest.getSurname())
            .ssn(teacherRequest.getSsn())
            .username(teacherRequest.getUsername())
            .birthDay(teacherRequest.getBirthDay())
            .birthPlace(teacherRequest.getBirthPlace())
            .password(teacherRequest.getPassword())
            .phoneNumber(teacherRequest.getPhoneNumber())
            .email(teacherRequest.getEmail())
            .isAdvisor(teacherRequest.isAdvisoryTeacher())
            .gender(teacherRequest.getGender())
            .build();
}
    public TeacherResponse mapTeacherToTeacherResponse(Teacher teacher){
        return TeacherResponse.builder()
                .userId(teacher.getId())
                .username(teacher.getUsername())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .birthDay(teacher.getBirthDay())
                .birthPlace(teacher.getBirthPlace())
                .ssn(teacher.getSsn())
                .phoneNumber(teacher.getPhoneNumber())
                .gender(teacher.getGender())
                .email(teacher.getEmail())
                .build();
    }


}
