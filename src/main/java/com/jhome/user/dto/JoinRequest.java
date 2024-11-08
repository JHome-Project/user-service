package com.jhome.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JoinRequest {

    @NotBlank(message = "Required Parameter Error")
    private String username;

    @NotBlank(message = "Required Parameter Error")
    private String password;

}
