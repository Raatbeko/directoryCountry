package com.example.directorycountry.dto.endoint.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportEndpointResponse {
    Long count;

    List<EndpointResponse> endpointResponses;
}
