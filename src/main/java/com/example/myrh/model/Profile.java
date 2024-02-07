package com.example.myrh.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    @OneToMany(mappedBy = "profile")
    private List<Question> questions = new ArrayList<>();
    @OneToMany(mappedBy = "profile")
    private List<JobSeeker> jobSeekers = new ArrayList<>();


}
