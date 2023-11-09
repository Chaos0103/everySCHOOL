package com.everyschool.consultservice.api.app.controller.consult;

import com.everyschool.consultservice.api.ApiResponse;
import com.everyschool.consultservice.api.app.controller.consult.request.CreateConsultRequest;
import com.everyschool.consultservice.api.app.controller.consult.response.CreateConsultResponse;
import com.everyschool.consultservice.api.app.service.consult.ConsultAppService;
import com.everyschool.consultservice.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/consult-service/v1/app/{schoolYear}/schools/{schoolId}/consults")
public class ConsultAppController {

    private final ConsultAppService consultAppService;
    private final TokenUtils tokenUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateConsultResponse> createConsult(
        @PathVariable Integer schoolYear,
        @PathVariable Long schoolId,
        @Valid @RequestBody CreateConsultRequest request
    ) {
        String userKey = tokenUtils.getUserKey();

        CreateConsultResponse response = consultAppService.createConsult(userKey, schoolYear, schoolId, request.toDto());

        return ApiResponse.created(response);
    }
}
