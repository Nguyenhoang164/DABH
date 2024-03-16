package com.example.dabh.service;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> showAll();
    Optional<T> findObjectById(int id);
    Iterable<T> findByAllOption(String keyword);
}
