package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
