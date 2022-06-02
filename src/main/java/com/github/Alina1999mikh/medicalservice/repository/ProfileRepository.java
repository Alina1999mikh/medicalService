package com.github.Alina1999mikh.medicalservice.repository;

import com.github.Alina1999mikh.medicalservice.entity.NoteEntity;
import com.github.Alina1999mikh.medicalservice.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, BigInteger> {
    Optional<ProfileEntity> findProfileByUsername(String username);

   // NoteEntity deleteByUui(UUID uuid);
}