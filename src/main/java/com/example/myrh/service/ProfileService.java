package com.example.myrh.service;

import com.example.myrh.dto.requests.ProfileRequest;
import com.example.myrh.dto.responses.ProfileResponse;

import java.util.List;

public interface ProfileService {
    List<ProfileResponse> getAllProfile();
}
