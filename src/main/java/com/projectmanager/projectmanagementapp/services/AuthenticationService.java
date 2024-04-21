package com.projectmanager.projectmanagementapp.services;

import com.projectmanager.projectmanagementapp.models.request.AuthenticationRequest;
import com.projectmanager.projectmanagementapp.models.request.RegisterRequest;
import com.projectmanager.projectmanagementapp.models.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}

