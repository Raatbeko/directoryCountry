package com.example.directorycountry.service.impl;

import com.example.directorycountry.dto.country.request.CountryRequest;
import com.example.directorycountry.dto.country.response.CountryResponse;
import com.example.directorycountry.entity.CountryEntity;
import com.example.directorycountry.exception.IsAllReadyExistsException;
import com.example.directorycountry.repository.CountryRepository;
import com.example.directorycountry.service.CountryService;
import com.example.directorycountry.service.EndpointLogService;
import com.example.directorycountry.service.FileCountryService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    final CountryRepository countryRepository;

    final FileCountryService fileCountryService;

    @Override
    public CountryResponse save(CountryRequest t) {
        CountryEntity countryEntity = countryRepository.getByName(t.getCountryName());
        if (countryEntity != null){
            throw new IsAllReadyExistsException("Такая страна уже сушествует!!!", HttpStatus.valueOf("it already exists"));
        }
        CountryEntity countryEntity1 =
                countryRepository
                        .save(CountryEntity
                                .builder()
                                .alphaCode(t.getAlphaCode())
                                .isActive(true)
                                .name(t.getCountryName())
                                .build());
        return CountryResponse
                .builder()
                .alphaCode(countryEntity1.getAlphaCode())
                .id(countryEntity1.getId())
                .countryName(countryEntity1.getName())
                .build();
    }

    @Override
    public List<CountryResponse> getAll() {
        List<CountryEntity> countryEntities = countryRepository.findAll();

        return countryEntities
                .stream()
                .map(countryEntities1 ->
                CountryResponse
                        .builder()
                        .id(countryEntities1.getId())
                        .countryName(countryEntities1.getName())
                        .alphaCode(countryEntities1.getAlphaCode())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public CountryResponse findById(Long id) {
        CountryEntity countryEntity = countryRepository.getById(id);
        return CountryResponse
                .builder()
                .fileResponse(fileCountryService.getByCountryId(id))
                .alphaCode(countryEntity.getAlphaCode())
                .countryName(countryEntity.getName())
                .id(countryEntity.getId())
                .build();
    }

    @Override
    public Boolean delete(Long id) {
        CountryEntity countryEntity = countryRepository.getById(id);
        if (countryEntity != null) {
            countryRepository.isActive(countryEntity.getId());
        return true;
        }
        return false;
    }

    @Override
    public CountryResponse search(String countryNameOrAlphaCode) {
        CountryEntity countryEntity = countryRepository.getByName(countryNameOrAlphaCode);
        CountryEntity countryEntity1 = countryRepository.getByAlphaCode(countryNameOrAlphaCode);
        if (countryEntity != null){
            return CountryResponse
                    .builder()
                    .id(countryEntity.getId())
                    .countryName(countryEntity.getName())
                    .alphaCode(countryEntity.getAlphaCode())
                    .build();
        }else if (countryEntity1 != null){
            return CountryResponse
                    .builder()
                    .id(countryEntity1.getId())
                    .countryName(countryEntity1.getName())
                    .alphaCode(countryEntity1.getAlphaCode())
                    .build();
        }
        return null;
    }
}
