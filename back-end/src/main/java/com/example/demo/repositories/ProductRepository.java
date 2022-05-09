package com.example.demo.repositories;

import com.example.demo.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    @Query(value =
            "select * from products where id in (" +
                    "select id from (" +
                    "(products p2 left join product_category pc on p2.id = pc.product_id) a " +
                    "left join product_technology pt on a.id = pt.product_id " +
                    ") as b " +
                    "where " +
                    "b.is_deleted = false " +
                    "and (coalesce(:gender_id) is null or (cast(b.gender_id as varchar) in (:gender_id))) " +
                    "and (coalesce(:sport_id) is null  or (cast(b.sport_id as varchar) in (:sport_id))) " +
                    "and ((:name is null) or (:name='') or (lower(name) like %:name%))" +
                    "and (coalesce(:category_ids) is null or (cast(b.category_id as varchar) in (:category_ids))) " +
                    "and (coalesce(:technology_ids) is null or (cast(b.technology_id as varchar) in (:technology_ids))))",
            nativeQuery = true)
    Page<ProductEntity> findAllFilter(@Param("category_ids") List<String> categoryIds,
                                      @Param("gender_id") List<String> genderId,
                                      @Param("sport_id") List<String> sportIds,
                                      @Param("technology_ids") List<String> technologyIds,
                                      @Param("name") String name,
                                      Pageable pageable);

    @Query(value = "select * from products where is_deleted=false and id=:productId", nativeQuery = true)
    Optional<ProductEntity> findById(@Param("productId") Long productId);
}
