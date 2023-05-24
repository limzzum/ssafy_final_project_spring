package com.ssafy.enjoytrip.model.dto.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserUpdateForm {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String userPwd;
    @NotEmpty
    private String userNewPwd;

}
