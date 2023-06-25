package com.project.schoolmanagment.controller;

import com.project.schoolmanagment.payload.request.ViceDeanRequest;
import com.project.schoolmanagment.payload.response.ResponseMessage;
import com.project.schoolmanagment.payload.response.ViceDeanReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("vicedean")
@RequiredArgsConstructor
public class ViceDean {

    private final ViceDeanService viceDeanService;

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @PostMapping("/save")
    public ResponseMessage<ViceDeanReponse> saveViceDean(@RequestBody @Valid ViceDeanRequest viceDeanRequest) {
        return viceDeanService.saveViceDean(viceDeanRequest);

    }
}
