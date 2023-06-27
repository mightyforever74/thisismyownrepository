package com.project.schoolmanagment.service;


import com.project.schoolmanagment.entity.concretes.ViceDean;
import com.project.schoolmanagment.entity.enums.RoleType;
import com.project.schoolmanagment.exception.ResourceNotFoundException;
import com.project.schoolmanagment.payload.mappers.ViceDeanDto;
import com.project.schoolmanagment.payload.request.ViceDeanRequest;
import com.project.schoolmanagment.payload.response.ResponseMessage;
import com.project.schoolmanagment.payload.response.ViceDeanResponse;
import com.project.schoolmanagment.repository.ViceDeanRepository;
import com.project.schoolmanagment.utils.CheckParameterUpdateMethod;
import com.project.schoolmanagment.utils.FieldControl;
import com.project.schoolmanagment.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViceDeanService {
    private final ViceDeanRepository viceDeanRepository;
    public final FieldControl fieldControl;
    public final ViceDeanDto viceDeanDto;
    public final PasswordEncoder passwordEncoder;
    public final UserRoleService userRoleService;

    public ResponseMessage<ViceDeanResponse> saveViceDean(ViceDeanRequest viceDeanRequest) {

        fieldControl.checkDuplicate(viceDeanRequest.getUsername(),
                viceDeanRequest.getSsn(),
                viceDeanRequest.getPhoneNumber());

        ViceDean viceDean = viceDeanDto.mapViceDeanRequestToViceDean(viceDeanRequest);
        viceDean.setPassword(passwordEncoder.encode(viceDeanRequest.getPassword()));
        viceDean.setUserRole(userRoleService.getUserRole(RoleType.ASSISTANT_MANAGER));

        ViceDean savedViceDean = viceDeanRepository.save(viceDean);

        return ResponseMessage.<ViceDeanResponse>builder()
                .message("Vice Dean Saved")
                .httpStatus(HttpStatus.CREATED)
                .object(viceDeanDto.mapViceDeanToViceDeanResponse(savedViceDean))
                .build();
    }

    public ResponseMessage<?> deleteViceDeanByUserId(Long viceDeanId) {
        viceDeanRepository.deleteById(viceDeanId);

        return ResponseMessage.builder()
                .message("Vice Dean deleted")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public ResponseMessage<ViceDeanResponse> findViceDeanByViceDeanId(Long viceDeanId) {

        return ResponseMessage.<ViceDeanResponse>builder()
                .message("Vice Dean Found")
                .httpStatus(HttpStatus.OK)
                .object(viceDeanDto.mapViceDeanToViceDeanResponse(isViceDeanExist(viceDeanId).get()))
                .build();
    }

    private Optional<ViceDean> isViceDeanExist(Long viceDeanId) {
        Optional<ViceDean> viceDean = viceDeanRepository.findById(viceDeanId);
        if (!viceDeanRepository.findById(viceDeanId).isPresent()) {
            throw new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE, viceDeanId));
        } else {
            return viceDean;
        }
    }

    public ResponseMessage<ViceDeanResponse> updateViceDean(ViceDeanRequest viceDeanRequest, Long viceDeanId) {
        Optional<ViceDean> viceDean = isViceDeanExist(viceDeanId);

        if (CheckParameterUpdateMethod.checkUniqueProperties(viceDean.get(), viceDeanRequest)) {
            fieldControl.checkDuplicate(viceDeanRequest.getUsername(),
                    viceDeanRequest.getSsn(),
                    viceDeanRequest.getPhoneNumber());
        }
        ViceDean updatedViceDean = viceDeanDto.mapViceDeanRequestToUpdatedViceDean(viceDeanRequest, viceDeanId);
        updatedViceDean.setPassword(passwordEncoder.encode(viceDeanRequest.getPassword()));
        ViceDean savedViceDean = viceDeanRepository.save(updatedViceDean);

        return ResponseMessage.<ViceDeanResponse>builder()
                .message("Vice Dean Updated")
                .httpStatus(HttpStatus.OK)
                .object(viceDeanDto.mapViceDeanToViceDeanResponse(savedViceDean))
                .build();
    }
//TODO learn about stream api
    public List<ViceDeanResponse> getAllViceDeans() {
        return viceDeanRepository.findAll()
                .stream()
                .map(viceDeanDto::mapViceDeanToViceDeanResponse)
                .collect(Collectors.toList());

    }
    public Page<ViceDeanResponse> getAllViceDeansByPage(int page, int size, String sort, String type){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
        if (Objects.equals(type, "desc")){
            pageable = PageRequest.of(page,size, Sort.by(sort).descending());
        }
        return viceDeanRepository.findAll(pageable).map(viceDeanDto::mapViceDeanToViceDeanResponse);



    }

}
