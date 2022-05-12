package com.example.directorycountry.service;

import com.example.directorycountry.dto.endoint.response.ReportEndpointResponse;

import java.util.List;

public interface EndpointLogService {
    Long getContLogs(String endpoint);

    ReportEndpointResponse getAllLog(String endpoint);
}
