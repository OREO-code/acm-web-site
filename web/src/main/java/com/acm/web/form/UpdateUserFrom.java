package com.acm.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserFrom {

    @NotBlank
    private String username;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword1;

    @NotBlank
    private String newPassword2;

}
