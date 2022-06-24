package com.example.demo.repositories.product;

import com.example.demo.entities.*;
import com.example.demo.repositories.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
  @Autowired private TestEntityManager testEntityManager;

  @Autowired private ProductRepository productRepository;

  @BeforeEach
  public void setUp() {}

  @Test
  public void findById_ShouldReturnOptionalOfProductEntity_WhenIdValid() {
    UserEntity userEntity = UserEntity.builder().email("email").password("password").build();
    ProductEntity productEntity = ProductEntity.builder().name("name").build();
    SizeEntity sizeEntity = SizeEntity.builder().name("name").build();
    ProductSizeEntity productSizeEntity =
        ProductSizeEntity.builder().product(productEntity).size(sizeEntity).build();
    UserRateProductEntity userRateProductEntity =
        UserRateProductEntity.builder().product(productEntity).user(userEntity).build();
    productEntity.setReviews(
        new HashSet<>() {
          {
            add(userRateProductEntity);
          }
        });
    productEntity.setSizes(
        new HashSet<>() {
          {
            add(productSizeEntity);
          }
        });
    testEntityManager.persist(userEntity);
    testEntityManager.persist(sizeEntity);
    testEntityManager.persist(productEntity);
    testEntityManager.flush();

    ProductEntity result = productRepository.findById(productEntity.getId()).orElse(null);

    assertThat(result, notNullValue());
    assertThat(result.getId(), is(100L));
    assertThat(result.getName(), is(productEntity.getName()));
    assertThat(result.getSizes(), contains(productSizeEntity));
    assertThat(result.getReviews(), contains(userRateProductEntity));
  }

  @Test
  public void findById_ShouldReturnOptionalOfNull_WhenIdNotExist() {
    UserEntity userEntity = UserEntity.builder().email("email").password("password").build();
    ProductEntity productEntity = ProductEntity.builder().name("name").build();
    SizeEntity sizeEntity = SizeEntity.builder().name("name").build();
    ProductSizeEntity productSizeEntity =
        ProductSizeEntity.builder().product(productEntity).size(sizeEntity).build();
    UserRateProductEntity userRateProductEntity =
        UserRateProductEntity.builder().product(productEntity).user(userEntity).build();
    productEntity.setReviews(
        new HashSet<>() {
          {
            add(userRateProductEntity);
          }
        });
    productEntity.setSizes(
        new HashSet<>() {
          {
            add(productSizeEntity);
          }
        });
    testEntityManager.persist(userEntity);
    testEntityManager.persist(sizeEntity);
    testEntityManager.persist(productEntity);
    testEntityManager.flush();

    // Make sure that passed in id won't exist
    ProductEntity result = productRepository.findById(productEntity.getId() + 1).orElse(null);

    assertThat(result, nullValue());
  }

  @Test
  public void findById_ShouldReturnOptionalOfNull_WhenIdExistButIsDeletedTrue() {
    UserEntity userEntity = UserEntity.builder().email("email").password("password").build();
    ProductEntity productEntity = ProductEntity.builder().name("name").isDeleted(true).build();
    SizeEntity sizeEntity = SizeEntity.builder().name("name").build();
    ProductSizeEntity productSizeEntity =
        ProductSizeEntity.builder().product(productEntity).size(sizeEntity).build();
    UserRateProductEntity userRateProductEntity =
        UserRateProductEntity.builder().product(productEntity).user(userEntity).build();
    productEntity.setReviews(
        new HashSet<>() {
          {
            add(userRateProductEntity);
          }
        });
    productEntity.setSizes(
        new HashSet<>() {
          {
            add(productSizeEntity);
          }
        });
    testEntityManager.persist(userEntity);
    testEntityManager.persist(sizeEntity);
    testEntityManager.persist(productEntity);
    testEntityManager.flush();

    ProductEntity result = productRepository.findById(productEntity.getId()).orElse(null);

    assertThat(result, nullValue());
  }
}
