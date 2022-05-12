package com.example.directorycountry.service;

import com.example.directorycountry.dto.file.request.FileRequest;
import com.example.directorycountry.dto.file.response.FileResponse;
import com.example.directorycountry.entity.FileCountryEntity;

public interface FileCountryService {

    FileResponse save(FileRequest fileRequest);

    FileResponse getByCountryId(Long countryId);

    Boolean delete(Long countryId);
}
