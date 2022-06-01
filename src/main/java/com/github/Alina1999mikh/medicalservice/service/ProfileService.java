package com.github.Alina1999mikh.medicalservice.service;

import com.github.Alina1999mikh.medicalservice.dto.CreateProfileRequest;
import com.github.Alina1999mikh.medicalservice.dto.NoteResponse;
import com.github.Alina1999mikh.medicalservice.dto.ProfileResponse;
import com.github.Alina1999mikh.medicalservice.entity.ProfileEntity;
import com.github.Alina1999mikh.medicalservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void createProfile(CreateProfileRequest request) {
        profileRepository.save(ProfileEntity.builder()
                .user_id(request.getUser_id())
                .login(request.getLogin())
                .password(request.getPassword())
                .fName(request.getFName())
                .sName(request.getSName())
                .date(request.getDate())
                .gender(request.getGender())
                .build()
        );
    }

    public Optional<ProfileResponse> findProfileByUserID(BigInteger userID) {
        return profileRepository.findProfileByUserID(userID)
                .map(it -> ProfileResponse.builder()
                        .user_id(it.getUser_id())
                        .login(it.getLogin())
                        .password(it.getPassword())
                        .fName(it.getFName())
                        .sName(it.getSName())
                        .date(it.getDate())
                        .gender(it.getGender())
                        .build());
    }
}