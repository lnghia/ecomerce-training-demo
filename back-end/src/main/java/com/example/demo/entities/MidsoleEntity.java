package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "midsoles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MidsoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "midsole_material_id", referencedColumnName = "id")
    private MidsoleMaterialEntity midsoleMaterial;

    @OneToOne
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    private TechnologyEntity technology;
}
