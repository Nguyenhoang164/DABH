package com.example.dabh.repository;

import com.example.dabh.model.Cart;
import org.springframework.data.repository.CrudRepository;
public interface ICartRepository extends CrudRepository<Cart , Integer> {
}
