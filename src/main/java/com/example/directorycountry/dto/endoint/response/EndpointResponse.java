package com.example.directorycountry.dto.endoint.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Jacksonized //missing
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EndpointResponse {

    String endpoint;

    LocalDateTime requestTime;
}
