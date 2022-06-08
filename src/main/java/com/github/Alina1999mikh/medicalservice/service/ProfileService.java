package com.github.Alina1999mikh.medicalservice.service;

import com.github.Alina1999mikh.medicalservice.dto.CreateProfileRequest;
import com.github.Alina1999mikh.medicalservice.dto.ProfileResponse;
import com.github.Alina1999mikh.medicalservice.entity.ProfileEntity;
import com.github.Alina1999mikh.medicalservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void createProfile(CreateProfileRequest request) {
        profileRepository.save(ProfileEntity.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .date(request.getDate())
                .gender(request.getGender())
                .build()
        );
    }

    public Optional<ProfileResponse> findProfileByUsername(String username) {
        return profileRepository.findProfileByUsername(username)
                .map(it -> ProfileResponse.builder()
                        .username(it.getUsername())
                        .firstName(it.getFirstName())
                        .secondName(it.getSecondName())
                        .date(it.getDate())
                        .gender(it.getGender())
                        .build());
    }
}