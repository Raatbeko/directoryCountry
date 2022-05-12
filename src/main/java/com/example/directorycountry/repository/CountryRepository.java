package com.example.directorycountry.repository;

import com.example.directorycountry.dto.country.response.CountryResponse;
import com.example.directorycountry.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    CountryEntity getByAlphaCode(String alphaCode);

    CountryEntity getByName(String name);

    @Query(nativeQuery = true,value = "update countries s set is_active =false where s.id =:id")
    CountryResponse isActive(Long id);

}
