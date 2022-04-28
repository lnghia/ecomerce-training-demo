package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "midsole_materials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MidsoleMaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;
}
