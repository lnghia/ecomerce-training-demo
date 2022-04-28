package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "uppers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UpperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "upper_material_id", referencedColumnName = "id")
    private UpperMaterialEntity upperMaterial;

    @OneToOne
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    private TechnologyEntity technology;
}
