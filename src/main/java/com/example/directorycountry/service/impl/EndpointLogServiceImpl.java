package com.example.directorycountry.service.impl;

import com.example.directorycountry.dto.endoint.response.EndpointResponse;
import com.example.directorycountry.dto.endoint.response.ReportEndpointResponse;
import com.example.directorycountry.entity.EndpointLogEntity;
import com.example.directorycountry.repository.EndpointLogRepository;
import com.example.directorycountry.service.EndpointLogService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EndpointLogServiceImpl implements EndpointLogService {

    final EndpointLogRepository endpointLogRepository;

    @Override
    public Long getContLogs(String endpoint) {
        return endpointLogRepository.countAllByEndpoint(endpoint);
    }

    @Override
    public ReportEndpointResponse getAllLog(String endpoint) {
        List<EndpointLogEntity> endpointLogEntities = endpointLogRepository.findByEndpoint(endpoint);
        return ReportEndpointResponse
                .builder()
                .count(getContLogs(endpoint))
                .endpointResponses(endpointLogEntities
                        .stream()
                        .map(endpoints -> EndpointResponse
                                .builder()
                                .endpoint(endpoints.getEndpoint())
                                .requestTime(endpoints.getCreateTime())
                                .build()).collect(Collectors.toList()))
                .build();
    }
}
