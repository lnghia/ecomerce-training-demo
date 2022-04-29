package com.example.demo.repositories;

import com.example.demo.entities.UpperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface UpperRepository extends JpaRepository<UpperEntity, Long> {
}
