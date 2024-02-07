package com.example.myrh.mapper;

import com.example.myrh.dto.requests.ProfileRequest;
import com.example.myrh.dto.responses.ProfileResponse;
import com.example.myrh.model.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper implements IMapper<Profile, ProfileRequest, ProfileResponse> {


    private final ModelMapper modelMapper;

    @Autowired
    public ProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProfileResponse toRes(Profile profile) {
        return this.modelMapper.map(profile, ProfileResponse.class);
    }

    @Override
    public ProfileRequest toReq(Profile profile) {
        return this.modelMapper.map(profile, ProfileRequest.class);
    }

    @Override
    public Profile resToEntity(ProfileResponse profileResponse) {
        return this.modelMapper.map(profileResponse, Profile.class);
    }

    @Override
    public Profile reqToEntity(ProfileRequest profileRequest) {
        return this.modelMapper.map(profileRequest, Profile.class);
    }
}