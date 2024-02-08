package com.example.myrh.repository;

import com.example.myrh.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;

public interface QuestionsRepository extends JpaRepository<Question, Long> {


    public Page<Question> findAllByProfileId(Long profileId, Pageable pageable);

}
