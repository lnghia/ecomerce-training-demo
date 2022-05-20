package com.example.demo.entities;

import com.example.demo.entities.idclasses.UserRateProductId;
import lombok.*;

import javax.persistence.*;

@Entity(name = "user_rate_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@IdClass(UserRateProductId.class)
public class UserRateProductEntity extends Auditable {
  @Id
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private ProductEntity product;

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;

  private int rating;

  private String comment;
}
