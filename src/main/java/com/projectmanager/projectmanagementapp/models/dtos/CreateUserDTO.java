package com.projectmanager.projectmanagementapp.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String username;

    private String email;

    private String password;

}
