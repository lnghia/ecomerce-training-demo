package com.example.demo.repositories;

import com.example.demo.entities.MidsoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidsoleRepository extends JpaRepository<MidsoleEntity, Long> {
}
