package com.example.directorycountry.service;

import com.example.directorycountry.dto.country.request.CountryRequest;
import com.example.directorycountry.dto.country.response.CountryResponse;

public interface CountryService extends BaseService<CountryResponse, CountryRequest>{
    CountryResponse search(String countryNameOrAlphaCode);
}
