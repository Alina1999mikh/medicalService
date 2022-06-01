package com.github.Alina1999mikh.medicalservice.controller;

import com.github.Alina1999mikh.medicalservice.dto.CreateProfileRequest;
import com.github.Alina1999mikh.medicalservice.dto.ProfileResponse;
import com.github.Alina1999mikh.medicalservice.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;

@RestController
@RequestMapping("/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    ResponseEntity<?> createProfile(@Validated @RequestBody CreateProfileRequest request) {
        profileService.createProfile(request);
        return ResponseEntity.created(URI.create("/v1/profile/" + request.getUser_id())).build();
    }

    @Operation(summary = "get", security = @SecurityRequirement(name="basicAuth"))
    @GetMapping("/{user_id}")
    ResponseEntity<ProfileResponse> findProfileByUserID(@PathVariable BigInteger user_id) {
        return ResponseEntity.of(profileService.findProfileByUserID(user_id));
    }
}