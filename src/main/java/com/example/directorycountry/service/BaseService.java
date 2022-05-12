package com.example.directorycountry.service;

import java.util.List;

public interface BaseService<Response, Request> {
    Response save(Request t);

    List<Response> getAll();

    Response findById(Long id);

    Boolean delete(Long id);
}