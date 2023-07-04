package com.project.schoolmanagment.utils;

import com.project.schoolmanagment.entity.abstracts.User;
import com.project.schoolmanagment.entity.concretes.Teacher;
import com.project.schoolmanagment.payload.request.BaseUserRequest;
import com.project.schoolmanagment.payload.request.TeacherRequest;

public class CheckParameterUpdateMethod {

    /**
     * @param user            a kind of entity that will be validated
     * @param baseUserRequest DTO from UI to be changed
     * @return true if they are the same
     */
    public static boolean checkUniqueProperties(User user, BaseUserRequest baseUserRequest) {
        return user.getSsn().equalsIgnoreCase(baseUserRequest.getSsn())
                || user.getPhoneNumber().equalsIgnoreCase(baseUserRequest.getPhoneNumber())
                || user.getUsername().equalsIgnoreCase(baseUserRequest.getUsername());
    }

    public static boolean checkUniquePropertiesForTeacher(Teacher teacher, TeacherRequest teacherRequest) {
        return teacher.getSsn().equalsIgnoreCase(teacherRequest.getSsn())
                || teacher.getPhoneNumber().equalsIgnoreCase(teacherRequest.getPhoneNumber())
                || teacher.getUsername().equalsIgnoreCase(teacherRequest.getUsername())
                || teacher.getEmail().equalsIgnoreCase(teacherRequest.getEmail());
    }


}
