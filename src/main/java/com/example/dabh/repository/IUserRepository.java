package com.example.dabh.repository;

import com.example.dabh.model.Role;
import com.example.dabh.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<Users , Integer> {
    Optional<Users> findUserByNameUserContaining(String name);
}
