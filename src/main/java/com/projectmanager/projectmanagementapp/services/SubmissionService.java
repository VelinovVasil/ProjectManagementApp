package com.projectmanager.projectmanagementapp.services;

import com.projectmanager.projectmanagementapp.models.dtos.CreateSubmissionDTO;
import com.projectmanager.projectmanagementapp.models.dtos.SubmissionDTO;

public interface SubmissionService {

    SubmissionDTO createSubmission(Long userId, Long taskId, CreateSubmissionDTO createSubmissionDTO);

    String deleteSubmission(Long submissionId);


}
