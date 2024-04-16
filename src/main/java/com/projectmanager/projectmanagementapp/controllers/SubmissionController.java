package com.projectmanager.projectmanagementapp.controllers;

import com.projectmanager.projectmanagementapp.models.dtos.CreateSubmissionDTO;
import com.projectmanager.projectmanagementapp.models.dtos.SubmissionDTO;
import com.projectmanager.projectmanagementapp.services.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("submissions")
@AllArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping("{userId}/{taskId}")
    public ResponseEntity<SubmissionDTO> createSubmission(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody CreateSubmissionDTO createSubmissionDTO) {
        return new ResponseEntity<>(this.submissionService.createSubmission(userId, taskId, createSubmissionDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete/{submissionId}")
    public ResponseEntity<String> deleteSubmission(@PathVariable Long submissionId) {
        return new ResponseEntity<>(this.submissionService.deleteSubmission(submissionId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionDTO> getSubmissionById(@PathVariable Long id) {
        return new ResponseEntity<>(this.submissionService.getSubmissionById(id), HttpStatus.OK);
    }
}
