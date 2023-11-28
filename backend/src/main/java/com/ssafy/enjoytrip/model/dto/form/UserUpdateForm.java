package com.ssafy.enjoytrip.model.dto.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserUpdateForm {
    @NotEmpty
    private int userNo;
    @NotEmpty
    private String curPwd;
    @NotEmpty
    private String newPwd;

}
