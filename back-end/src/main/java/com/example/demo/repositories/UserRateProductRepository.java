package com.example.demo.repositories;

import com.example.demo.entities.UserRateProductEntity;
import com.example.demo.entities.idclasses.UserRateProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRateProductRepository extends JpaRepository<UserRateProductEntity, UserRateProductId> {
    @Query(value = "select * from user_rate_product where product_id=:productId " +
            "order by last_modified_date desc",
            countQuery = "select count(user_id) from user_rate_product where product_id=:productId " +
                    "group by last_modified_date " +
                    "order by last_modified_date desc",
            nativeQuery = true)
    Page<UserRateProductEntity> getLatestRatings(@Param("productId") Long productId, Pageable pageable);

    @Query(value = "select * from user_rate_product " +
            "where user_id=:userId and product_id=:productId",
            nativeQuery = true)
    Optional<UserRateProductEntity> findByUserIdProductId(@Param("userId") Long userId,
                                                          @Param("productId") Long productId);
}
