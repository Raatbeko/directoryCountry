package com.example.directorycountry.controller;

import com.example.directorycountry.dto.country.request.CountryRequest;
import com.example.directorycountry.dto.country.response.CountryResponse;
import com.example.directorycountry.dto.endoint.response.ReportEndpointResponse;
import com.example.directorycountry.dto.file.request.FileRequest;
import com.example.directorycountry.dto.file.response.FileResponse;
import com.example.directorycountry.service.CountryService;
import com.example.directorycountry.service.FileCountryService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/country")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class CountryController {

    final CountryService countryService;

    final FileCountryService fileCountryService;

    @PostMapping("/add")
    public CountryResponse save(CountryRequest countryRequest){
        return countryService.save(countryRequest);
    }

    @DeleteMapping("/delete")
    public Boolean delete(Long id){
        return countryService.delete(id);
    }

    @GetMapping("/all")
    public List<CountryResponse> getAll(){
        return countryService.getAll();
    }

    @PostMapping("/search")
    public CountryResponse search(String countryNameOrAlphaCode){
        return countryService.search(countryNameOrAlphaCode);
    }

    @GetMapping("/report-endpoints")
    public ReportEndpointResponse report(){
        return null;
    }

    @GetMapping("/add-photo")
    public FileResponse saveFile(FileRequest fileRequest){
        return fileCountryService.save(fileRequest);
    }

}
