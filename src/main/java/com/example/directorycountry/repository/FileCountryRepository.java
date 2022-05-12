package com.example.directorycountry.repository;

import com.example.directorycountry.entity.FileCountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCountryRepository extends JpaRepository<FileCountryEntity, Long> {
    FileCountryEntity getByCountryEntityId(Long id);
}
