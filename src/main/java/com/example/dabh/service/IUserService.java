package com.example.dabh.service;

import com.example.dabh.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserService extends IGenerateService<Users> {
    Optional<Users> findUserByName(String name);
}
