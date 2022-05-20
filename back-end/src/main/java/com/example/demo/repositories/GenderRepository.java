package com.example.demo.repositories;

import com.example.demo.entities.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {}
