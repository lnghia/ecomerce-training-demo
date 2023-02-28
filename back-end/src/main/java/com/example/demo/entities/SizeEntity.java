package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sizes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SizeEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String description;

  //    @OneToMany(mappedBy = "size")
  //    private Set<ProductSizeEntity> products;
}
