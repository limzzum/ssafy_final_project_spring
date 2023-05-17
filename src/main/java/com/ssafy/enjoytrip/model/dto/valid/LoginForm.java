package com.ssafy.enjoytrip.model.dto.valid;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String userPwd;
}
