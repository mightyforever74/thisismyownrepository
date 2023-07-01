package com.project.schoolmanagment.controller;

import com.project.schoolmanagment.payload.request.abstracts.EducationTermRequest;
import com.project.schoolmanagment.payload.response.EducationTermResponse;
import com.project.schoolmanagment.payload.response.ResponseMessage;
import com.project.schoolmanagment.service.EducationTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("educationTerms")
@RequiredArgsConstructor
public class EducationTermController {

    private final EducationTermService educationTermService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<EducationTermResponse> saveEducationTerm(@RequestBody @Valid EducationTermRequest educationTermRequest) {
        return educationTermService.saveEducationTerm(educationTermRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANTMANAGER','TEACHER')")
    @GetMapping("/{id")
    public EducationTermResponse getEducationTermById(@PathVariable Long id) {
        return educationTermService.getEducationTermById(id);
    }
}
