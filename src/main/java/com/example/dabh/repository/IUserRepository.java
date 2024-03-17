package com.example.dabh.repository;

import com.example.dabh.model.Role;
import com.example.dabh.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends CrudRepository<Users , Integer> {
    Optional<Users> findUserByNameUserContaining(String name);
}
