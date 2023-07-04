package com.project.schoolmanagment.service;

import com.project.schoolmanagment.entity.concretes.AdvisoryTeacher;
import com.project.schoolmanagment.entity.concretes.Teacher;
import com.project.schoolmanagment.entity.enums.RoleType;
import com.project.schoolmanagment.exception.ResourceNotFoundException;
import com.project.schoolmanagment.payload.mappers.AdvisoryTeacherDto;
import com.project.schoolmanagment.repository.AdvisoryTeacherRepository;
import com.project.schoolmanagment.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvisoryTeacherService {

    private final AdvisoryTeacherRepository advisoryTeacherRepository;

    private final UserRoleService userRoleService;

    private final AdvisoryTeacherDto advisoryTeacherDto;

    public void saveAdvisoryTeacher(Teacher teacher) {

        AdvisoryTeacher advisoryTeacher = advisoryTeacherDto.mapTeacherToAdvisoryTeacher(teacher);//teacheri advisoryteacher a maplemek
        advisoryTeacher.setUserRole(userRoleService.getUserRole(RoleType.ADVISORY_TEACHER));//rolunu degistirmek

        advisoryTeacherRepository.save(advisoryTeacher);//save ledik db e

    }

    public void updateAdvisoryTeacher(boolean status, Teacher teacher) {

        //we are checking the DB to find the correct advisory teacher
        Optional<AdvisoryTeacher> advisoryTeacher = advisoryTeacherRepository.getAdvisoryTeacherByTeacher_Id(teacher.getId());// db kontrol ettik gercekten advisory teacherimiz var mi

        AdvisoryTeacher.AdvisoryTeacherBuilder advisoryTeacherBuilder = AdvisoryTeacher.builder()
                .teacher(teacher)//advisory techer olusturuyoruz
                .userRole(userRoleService.getUserRole(RoleType.ADVISORY_TEACHER));

        //do we really have an advisory teacher in DB

        if (advisoryTeacher.isPresent()) {// advisory teacherimiz yoks a ikinci else yok yani birsey yapmadik ama varsa
            //will be this new updated teacher really an advisory teacher

            if (status) {
                advisoryTeacherBuilder.id(advisoryTeacher.get().getId());
                advisoryTeacherRepository.save(advisoryTeacherBuilder.build());
            } else {
                //these teacher is not advisory teacher anymore
                advisoryTeacherRepository.deleteById(advisoryTeacher.get().getId());
            }
        }
    }
    public AdvisoryTeacher getAdvisoryTeacherById(Long advisoryTeacherId){
        return advisoryTeacherRepository
                .findById(advisoryTeacherId)
                .orElseThrow(()->
                        new ResourceNotFoundException(String.format(Messages.NOT_FOUND_ADVISOR_MESSAGE,advisoryTeacherId)));
    }



}
