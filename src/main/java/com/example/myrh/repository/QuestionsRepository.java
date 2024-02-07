package com.example.myrh.repository;

import com.example.myrh.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Long> {

}
