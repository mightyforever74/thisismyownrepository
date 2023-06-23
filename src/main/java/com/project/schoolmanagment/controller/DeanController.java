package com.project.schoolmanagment.controller;

import com.project.schoolmanagment.payload.request.DeanRequest;
import com.project.schoolmanagment.payload.response.DeanResponse;
import com.project.schoolmanagment.payload.response.ResponseMessage;
import com.project.schoolmanagment.security.DeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("dean")
/**
 * Class level authorization
 */
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
public class DeanController {
    private final DeanService deanService;

    @PostMapping("/save")
    public ResponseMessage <DeanResponse> save(@RequestBody @Valid DeanRequest deanRequest){
        return deanService.save(deanRequest);
    }
    @PutMapping("/update/{userId}")
    public ResponseMessage<DeanResponse> update(@RequestBody @Valid DeanRequest deanRequest,
                                                @PathVariable Long userId){
        return deanService.update(deanRequest,userId);
    }
}
