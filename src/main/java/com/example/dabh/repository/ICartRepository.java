package com.example.dabh.repository;

import com.example.dabh.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartItem,Integer> {

}
