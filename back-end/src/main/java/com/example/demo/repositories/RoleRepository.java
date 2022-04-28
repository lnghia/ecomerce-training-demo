package com.example.demo.repositories;

import com.example.demo.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query(value = "SELECT * FROM roles WHERE name=:name AND is_deleted=false", nativeQuery = true)
    Optional<RoleEntity> findByName(String name);

    @Query(value = "SELECT * FROM roles WHERE id=:roleId AND is_deleted=false", nativeQuery = true)
    RoleEntity getById(long roleId);

    @Query(value = "SELECT * FROM roles WHERE id=:id AND is_deleted=false", nativeQuery = true)
    Optional<RoleEntity> findById(long id);
}
