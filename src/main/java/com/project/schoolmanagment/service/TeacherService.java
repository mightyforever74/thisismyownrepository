package com.project.schoolmanagment.service;

import com.project.schoolmanagment.entity.concretes.LessonProgram;
import com.project.schoolmanagment.entity.concretes.Teacher;
import com.project.schoolmanagment.entity.enums.RoleType;
import com.project.schoolmanagment.exception.ResourceNotFoundException;
import com.project.schoolmanagment.payload.mappers.TeacherDto;
import com.project.schoolmanagment.payload.request.TeacherRequest;
import com.project.schoolmanagment.payload.response.ResponseMessage;
import com.project.schoolmanagment.payload.response.TeacherResponse;
import com.project.schoolmanagment.repository.TeacherRepository;
import com.project.schoolmanagment.utils.Messages;
import com.project.schoolmanagment.utils.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final LessonProgramService lessonProgramService;

    private final ServiceHelpers serviceHelpers;

    private final TeacherDto teacherDto;

    private final UserRoleService userRoleService;

    private final PasswordEncoder passwordEncoder;

    private final AdvisoryTeacherService advisoryTeacherService;


    public ResponseMessage<TeacherResponse> saveTeacher(TeacherRequest teacherRequest) {
        //need to get lesson programs
        Set<LessonProgram> lessonProgramSet = lessonProgramService.getLessonProgramById(teacherRequest.getLessonsIdList());

        //    if (lessonProgramSet.isEmpty()) {
        //        throw new BadRequestException(Messages.NOT_FOUND_LESSON_PROGRAM_MESSAGE_WITHOUT_ID_INFO);

        //    }
        serviceHelpers.checkDuplicate(teacherRequest.getUsername(),
                teacherRequest.getSsn(),
                teacherRequest.getPhoneNumber(),
                teacherRequest.getEmail());
        Teacher teacher = teacherDto.mapTeacherRequestToteacher(teacherRequest);
        teacher.setUserRole(userRoleService.getUserRole(RoleType.TEACHER));
        teacher.setLessonsProgramList(lessonProgramSet);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));

        Teacher savedTeacher = teacherRepository.save(teacher);
        if (teacherRequest.isAdvisoryTeacher()) {
            advisoryTeacherService.saveAdvisoryTeacher(teacher);
        }
        return ResponseMessage.<TeacherResponse>builder()
                .message("Teacher Saved successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(teacherDto.mapTeacherToTeacherResponse(savedTeacher))
                .build();
    }

    public List<TeacherResponse> getAllTeacher() {

        return teacherRepository.findAll()
                .stream()
                .map(teacherDto::mapTeacherToTeacherResponse)
                .collect(Collectors.toList());
    }

    public List<TeacherResponse> getTeacherByName(String teacherName) {
        return teacherRepository.getTeachersByNameContaining(teacherName)
                .stream()
                .map(teacherDto::mapTeacherToTeacherResponse)
                .collect(Collectors.toList());

    }

    public ResponseMessage deleteTeacherById(Long id) {
        isTeacherExist(id);
        teacherRepository.deleteById(id);

        return ResponseMessage.builder()
                .message("Teacher Deleted Successfully")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private void isTeacherExist(Long id) {
        teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE,id)));
    }
public  ResponseMessage<TeacherResponse>getTeacherById(Long id){
        isTeacherExist(id);

        return ResponseMessage.<TeacherResponse>builder()
                .object(teacherDto.mapTeacherToTeacherResponse(teacherRepository.findById(id).get()))
                .message("Teacher successfully found")
                .httpStatus(HttpStatus.OK)
                .build();
}
}
