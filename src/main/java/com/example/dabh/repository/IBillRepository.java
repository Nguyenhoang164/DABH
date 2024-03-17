package com.example.dabh.repository;

import com.example.dabh.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepository extends CrudRepository<Bill , Integer> {
}
