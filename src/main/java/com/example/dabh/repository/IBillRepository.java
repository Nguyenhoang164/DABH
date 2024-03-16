package com.example.dabh.repository;

import com.example.dabh.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface IBillRepository extends CrudRepository<Bill , Integer> {
}
