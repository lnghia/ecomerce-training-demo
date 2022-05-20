package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "genders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GenderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  //    @OneToMany(mappedBy = "gender", fetch = FetchType.LAZY)
  //    private Set<ProductEntity> products;
}
