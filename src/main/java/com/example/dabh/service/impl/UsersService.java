package com.example.dabh.service.impl;

import com.example.dabh.model.Users;
import com.example.dabh.repository.IUserRepository;
import com.example.dabh.service.IUserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsersService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<Users> showAll() {
        return null;
    }

    @Override
    public Optional<Users> findObjectById(int id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Users> findByAllOption(String keyword) {
        return null;
    }

    @Override
    public Optional<Users> findUserByName(String name) {
        return userRepository.findUserByNameUser(name);
    }
}
