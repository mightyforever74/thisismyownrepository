package com.project.schoolmanagment.utils;

import com.project.schoolmanagment.entity.abstracts.User;
import com.project.schoolmanagment.payload.request.BaseUserRequest;

public class CheckParameterUpdateMethod {

    /**
     *
     * @param user a kind of entity that will be validated
     * @param baseUserRequest DTO from UI to be changed
     * @return true if they are the same
     */
    public static boolean checkUniqueProperties(User user, BaseUserRequest baseUserRequest){
        return user.getSsn().equalsIgnoreCase(baseUserRequest.getSsn())
                || user.getPhoneNumber().equalsIgnoreCase(baseUserRequest.getPhoneNumber())
                || user.getUsername().equalsIgnoreCase(baseUserRequest.getUsername());
    }



}
