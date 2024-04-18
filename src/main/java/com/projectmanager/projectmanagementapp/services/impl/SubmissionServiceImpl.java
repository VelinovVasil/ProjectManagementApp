package com.projectmanager.projectmanagementapp.services.impl;

import com.projectmanager.projectmanagementapp.models.dtos.CreateSubmissionDTO;
import com.projectmanager.projectmanagementapp.models.dtos.SubmissionDTO;
import com.projectmanager.projectmanagementapp.models.entities.Submission;
import com.projectmanager.projectmanagementapp.models.entities.Task;
import com.projectmanager.projectmanagementapp.models.entities.User;
import com.projectmanager.projectmanagementapp.repositories.SubmissionRepository;
import com.projectmanager.projectmanagementapp.repositories.TaskRepository;
import com.projectmanager.projectmanagementapp.repositories.UserRepository;
import com.projectmanager.projectmanagementapp.services.SubmissionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.projectmanager.projectmanagementapp.util.RepositoryUtil.findById;

@Service
@AllArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private SubmissionRepository submissionRepository;

    private UserRepository userRepository;

    private TaskRepository taskRepository;

    private ModelMapper modelMapper;


    @Override
    public SubmissionDTO createSubmission(Long userId, Long taskId, CreateSubmissionDTO createSubmissionDTO) {

        Submission submission = this.modelMapper.map(createSubmissionDTO, Submission.class);
        submission.setSubmissionDate(LocalDateTime.now());
        User user = findById(this.userRepository, userId);
        submission.setUser(user);
        Task task = findById(this.taskRepository, taskId);
        submission.setTask(task);

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

        Submission submission = findById(this.submissionRepository, submissionId);

        SubmissionDTO dto = this.modelMapper.map(submission, SubmissionDTO.class);

        dto.setUserId(submission.getUser().getId());
        dto.setTaskId(submission.getTask().getId());

        return dto;
    }
}
