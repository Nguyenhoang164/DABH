package com.example.dabh.repository;

import com.example.dabh.model.Role;
import com.example.dabh.model.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface IRoleRepository extends CrudRepository<Role , Integer> {
     Iterable<Role> findRoleByUsers(Set<Users> users);
}
