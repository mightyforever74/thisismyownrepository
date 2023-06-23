package com.project.schoolmanagment.payload.dto;

import com.project.schoolmanagment.entity.concretes.Dean;
import com.project.schoolmanagment.payload.request.DeanRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DeanDto {

    public Dean mapDeanRequestToDean(DeanRequest deanRequest){
        return Dean.builder()
                .username(deanRequest.getUsername())
                .name(deanRequest.getName())
                .surname(deanRequest.getSurname())
                .password(deanRequest.getPassword())
                .ssn(deanRequest.getSsn())
                .birthDay(deanRequest.getBirthDay())
                .birthPlace(deanRequest.getBirthPlace())
                .phoneNumber(deanRequest.getPhoneNumber())
                .gender(deanRequest.getGender())
                .build();
    }
}
