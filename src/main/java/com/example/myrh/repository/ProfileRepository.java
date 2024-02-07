package com.example.myrh.repository;

import com.example.myrh.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
