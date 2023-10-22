package com.everyschool.userservice.api.controller.user.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class JoinUserRequest {

    @NotNull
    private Integer userCode;

    @NotEmpty
    @Size(max = 100)
    private String email;

    @NotEmpty
    @Size(max = 20)
    private String password;

    @NotEmpty
    @Size(max = 20)
    private String name;

    @NotEmpty
    @Size(min = 10, max = 10)
    private String birth;

    @Builder
    private JoinUserRequest(Integer userCode, String email, String password, String name, String birth) {
        this.userCode = userCode;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
    }
}
