package com.projectmanager.projectmanagementapp.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projectmanager.projectmanagementapp.handler.exceptions.EntityNotFoundException;
import com.projectmanager.projectmanagementapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationBeanConfiguration {

    private final UserRepository userRepository;

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> this.userRepository.findUserByUsername(username)
                .orElseThrow(() ->  new EntityNotFoundException("Username not found"));
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
