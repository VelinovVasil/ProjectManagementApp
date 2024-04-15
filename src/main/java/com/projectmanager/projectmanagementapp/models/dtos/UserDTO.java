package com.projectmanager.projectmanagementapp.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private Set<Long> tasks;
}
