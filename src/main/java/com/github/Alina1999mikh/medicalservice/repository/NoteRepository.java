package com.github.Alina1999mikh.medicalservice.repository;

import com.github.Alina1999mikh.medicalservice.entity.NoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, UUID> {
    Optional<NoteEntity> findByUuid(UUID uuid);

    NoteEntity deleteByUuid(UUID uuid);
}
