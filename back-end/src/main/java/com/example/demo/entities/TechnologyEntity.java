package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "technologies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TechnologyEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String description;
}
