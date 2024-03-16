package com.example.dabh.repository;

import com.example.dabh.model.Role;
import com.example.dabh.model.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface IRoleRepository extends CrudRepository<Role , Integer> {
     Iterable<Role> findRoleByUsers(Set<Users> users);
}
