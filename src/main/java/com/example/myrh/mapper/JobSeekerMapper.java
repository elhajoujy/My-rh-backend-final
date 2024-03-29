package com.example.myrh.mapper;

import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.JobSeekerRes;
import com.example.myrh.model.JobSeeker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobSeekerMapper implements IMapper<JobSeeker, JobSeekerReq, JobSeekerRes> {

    private final ModelMapper modelMapper;

    @Autowired
    public JobSeekerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public JobSeekerRes toRes(JobSeeker jobSeeker) {
        return this.modelMapper.map(jobSeeker, JobSeekerRes.class);
    }

    @Override
    public JobSeekerReq toReq(JobSeeker jobSeeker) {
        return this.modelMapper.map(jobSeeker, JobSeekerReq.class);
    }

    @Override
    public JobSeeker resToEntity(JobSeekerRes jobSeekerRes) {
        return this.modelMapper.map(jobSeekerRes, JobSeeker.class);
    }

    @Override
    public JobSeeker reqToEntity(JobSeekerReq jobSeekerReq) {
        return this.modelMapper.map(jobSeekerReq, JobSeeker.class);
    }
}
