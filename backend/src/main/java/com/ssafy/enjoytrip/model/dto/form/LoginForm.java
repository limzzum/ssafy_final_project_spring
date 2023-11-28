package com.ssafy.enjoytrip.model.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class LoginForm {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String userPwd;
}
