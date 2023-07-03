package com.project.schoolmanagment.service;

import com.project.schoolmanagment.entity.concretes.AdvisoryTeacher;
import com.project.schoolmanagment.entity.concretes.Teacher;
import com.project.schoolmanagment.entity.enums.RoleType;
import com.project.schoolmanagment.payload.mappers.AdvisoryTeacherDto;
import com.project.schoolmanagment.repository.AdvisoryTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvisoryTeacherService {

    private  final AdvisoryTeacherRepository advisoryTeacherRepository;

    private final  UserRoleService userRoleService;

    private final AdvisoryTeacherDto advisoryTeacherDto;

    public  void saveAdvisoryTeacher(Teacher teacher){
        AdvisoryTeacher advisoryTeacher= AdvisoryTeacherDto.mapTeacherToAdvisoryTeacher(teacher);
        advisoryTeacher.getUserRole(userRoleService.getAllUserRole(RoleType.ADVISORY_TEACHER));

        advisoryTeacherRepository.save(advisoryTeacher);


    }



}
