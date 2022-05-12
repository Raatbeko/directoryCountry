package com.example.directorycountry.dto.country.response;

import com.example.directorycountry.dto.file.response.FileResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryResponse {

    Long id;

    String countryName;

    String alphaCode;

    FileResponse fileResponse;
}
