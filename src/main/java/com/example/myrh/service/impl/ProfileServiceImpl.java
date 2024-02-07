package com.example.myrh.service.impl;

import com.example.myrh.dto.responses.ProfileResponse;
import com.example.myrh.mapper.JobSeekerMapper;
import com.example.myrh.mapper.ProfileMapper;
import com.example.myrh.model.Profile;
import com.example.myrh.repository.JobSeekerRepo;
import com.example.myrh.repository.ProfileRepository;
import com.example.myrh.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository repository;

    private final ProfileMapper mapper;

    @Autowired
    public ProfileServiceImpl(ProfileRepository repository,ProfileMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProfileResponse> getAllProfile() {
        List<Profile> profiles = repository.findAll();

        return profiles.stream()
                .map(mapper::toRes)
                .collect(Collectors.toList());
    }
}
