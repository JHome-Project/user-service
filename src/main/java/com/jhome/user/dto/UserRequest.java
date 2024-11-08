package com.jhome.user.dto;

import lombok.Getter;

@Getter
public class UserRequest {

    private String username;
    private String password;
    private String role;
    private String type;
    private String name;
    private String email;
    private String phone;

}
