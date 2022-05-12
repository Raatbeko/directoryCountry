package com.example.directorycountry.service.impl;

import com.example.directorycountry.dto.file.request.FileRequest;
import com.example.directorycountry.dto.file.response.FileResponse;
import com.example.directorycountry.entity.CountryEntity;
import com.example.directorycountry.entity.FileCountryEntity;
import com.example.directorycountry.entity.FileEntity;
import com.example.directorycountry.repository.CountryRepository;
import com.example.directorycountry.repository.FileCountryRepository;
import com.example.directorycountry.repository.FileRepository;
import com.example.directorycountry.service.FileCountryService;
import com.example.directorycountry.service.FileService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FileCountryServiceImpl implements FileCountryService {

    final FileCountryRepository fileCountryRepository;

    final CountryRepository countryRepository;

    final FileService fileService;

    final FileRepository fileRepository;

    @Override
    public FileResponse save(FileRequest fileRequest) {

        FileResponse fileResponse = fileService.save(fileRequest);
        CountryEntity countryEntity = countryRepository.getById(fileRequest.getCountryId());
        fileResponse.setCountryId(countryEntity.getId());

        fileCountryRepository
                .save(FileCountryEntity
                        .builder()
                        .countryEntity(countryEntity)
                        .fileEntity(fileRepository.getById(fileResponse.getId()))
                        .build());

        return fileResponse;
    }

    @Override
    public FileResponse getByCountryId(Long countryId) {
        FileCountryEntity fileCountryEntity  = fileCountryRepository.getByCountryEntityId(countryId);
        return FileResponse
                .builder()
                .countryId(fileCountryEntity.getCountryEntity().getId())
                .id(fileCountryEntity.getFileEntity().getId())
                .url(fileCountryEntity.getFileEntity().getUrl())
                .build();
    }

    @Override
    public Boolean delete(Long countryId) {
        return true;
    }
}
