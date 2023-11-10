package com.everyschool.callservice.docs.donotdisturb;

import com.everyschool.callservice.api.client.UserServiceClient;
import com.everyschool.callservice.api.controller.donotdisturb.DoNotDisturbQueryController;
import com.everyschool.callservice.api.controller.donotdisturb.response.DoNotDisturbResponse;
import com.everyschool.callservice.api.service.donotdisturb.DoNotDisturbQueryService;
import com.everyschool.callservice.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DoNotDisturbQueryControllerDocsTest extends RestDocsSupport {

    private final UserServiceClient userServiceClient = mock(UserServiceClient.class);
    private final DoNotDisturbQueryService doNotDisturbQueryService = mock(DoNotDisturbQueryService.class);
    @Override
    protected Object initController() {
        return new DoNotDisturbQueryController(doNotDisturbQueryService);
    }

    @DisplayName("내 방해 금지 목록 조회 API")
    @Test
    void searchDoNotDisturbs() throws Exception {

        DoNotDisturbResponse response1 = DoNotDisturbResponse.builder()
                .startTime(LocalDateTime.now().minusDays(1))
                .endTime(LocalDateTime.now().minusHours(23))
                .isActivate(false)
                .build();

        DoNotDisturbResponse response2 = DoNotDisturbResponse.builder()
                .startTime(LocalDateTime.now().minusHours(2))
                .endTime(LocalDateTime.now().minusHours(1))
                .isActivate(true)
                .build();

        List<DoNotDisturbResponse> dList = List.of(response1, response2);

        given(doNotDisturbQueryService.searchMyDoNotDisturbs(anyString()))
                .willReturn(dList);

        mockMvc.perform(
                        get("/call-service/v1/do-not-disturbs/")
                            .header("Authorization", "Bearer Access Token")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-do-not-disturbs",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.ARRAY)
                                        .description("응답 데이터"),
                                fieldWithPath("data[].startTime").type(JsonFieldType.ARRAY)
                                        .description("시작 시간"),
                                fieldWithPath("data[].endTime").type(JsonFieldType.ARRAY)
                                        .description("끝 나는 시간"),
                                fieldWithPath("data[].isActivate").type(JsonFieldType.BOOLEAN)
                                        .description("활성화 상태")
                        )
                ));
    }
}
