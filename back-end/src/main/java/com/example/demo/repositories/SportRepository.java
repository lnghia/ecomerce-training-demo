package com.example.demo.repositories;

import com.example.demo.entities.SportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<SportEntity, Long> {

}
