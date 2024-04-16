package com.projectmanager.projectmanagementapp.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubmissionDTO {

    private String comment;

    private LocalDateTime submissionDate;

    private Long user;

    private Long task;
}
