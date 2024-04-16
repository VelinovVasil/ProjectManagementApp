package com.projectmanager.projectmanagementapp.services.impl;

import com.projectmanager.projectmanagementapp.models.dtos.CreateSubmissionDTO;
import com.projectmanager.projectmanagementapp.models.dtos.SubmissionDTO;
import com.projectmanager.projectmanagementapp.models.entities.Submission;
import com.projectmanager.projectmanagementapp.repositories.SubmissionRepository;
import com.projectmanager.projectmanagementapp.repositories.TaskRepository;
import com.projectmanager.projectmanagementapp.repositories.UserRepository;
import com.projectmanager.projectmanagementapp.services.SubmissionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private SubmissionRepository submissionRepository;

    private UserRepository userRepository;

    private TaskRepository taskRepository;

    private ModelMapper modelMapper;

    // TODO: Error handling

    @Override
    public SubmissionDTO createSubmission(Long userId, Long taskId, CreateSubmissionDTO createSubmissionDTO) {

        Submission submission = this.modelMapper.map(createSubmissionDTO, Submission.class);
        submission.setSubmissionDate(LocalDateTime.now());
        submission.setUser(this.userRepository.findById(userId).orElse(null));
        submission.setTask(this.taskRepository.findById(taskId).orElse(null));

        this.submissionRepository.saveAndFlush(submission);

        SubmissionDTO dto = modelMapper.map(submission, SubmissionDTO.class);
        dto.setTaskId(taskId);
        dto.setUserId(userId);

        return dto;
    }

    @Override
    public String deleteSubmission(Long submissionId) {

        this.submissionRepository.deleteById(submissionId);

        return "Successfully deleted submission";
    }

    @Override
    public SubmissionDTO getSubmissionById(Long submissionId) {

        Submission submission = this.submissionRepository.findById(submissionId).orElse(null);
        SubmissionDTO dto = this.modelMapper.map(submission, SubmissionDTO.class);
        dto.setUserId(submission.getUser().getId());
        dto.setTaskId(submission.getTask().getId());

        return dto;
    }
}
