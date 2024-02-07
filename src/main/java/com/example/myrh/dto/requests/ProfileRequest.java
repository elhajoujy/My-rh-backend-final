package com.example.myrh.dto.requests;

import com.example.myrh.model.JobSeeker;
import com.example.myrh.model.Question;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProfileRequest {
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 255, message = "Le nom ne peut pas dépasser 255 caractères")
    private String name;

    @NotBlank(message = "La description ne peut pas être vide")
    private String description;

    @NotBlank(message = "L'image ne peut pas être vide")
    private String image;

}
