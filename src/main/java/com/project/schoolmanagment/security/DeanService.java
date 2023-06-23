package com.project.schoolmanagment.security;

import com.project.schoolmanagment.entity.concretes.Dean;
import com.project.schoolmanagment.payload.dto.DeanDto;
import com.project.schoolmanagment.payload.request.DeanRequest;
import com.project.schoolmanagment.payload.response.ResponseMessage;
import com.project.schoolmanagment.utils.FieldControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeanService {
    private final  FieldControl fieldControl;
    private final DeanDto deanDto;
    public ResponseMessage<DeanRequest> save (DeanRequest deanRequest){
        fieldControl.checkDuplicate(deanRequest.getUsername(),deanRequest.getSsn(),deanRequest.getPhoneNumber());
        Dean dean=deanDto.mapDeanRequestToDean(deanRequest);
        return null;
    }

}
