package com.projectmanager.projectmanagementapp.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskDTO {

    private String title;

    private String description;

    private String deadline;

    private Long taskAdmin;

}
