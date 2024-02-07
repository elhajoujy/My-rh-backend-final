package com.example.myrh.dto.responses;

import com.example.myrh.model.Offer;
import com.example.myrh.model.Profile;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JobSeekerRes {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;
    private boolean isEnabled;
    private boolean isAvalidated;
    private LocalDate lastExamPassedDate;
    private ProfileResponse profile;
    //Set<Offer> offers = new HashSet<>();

}
