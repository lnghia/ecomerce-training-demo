package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private GenderEntity gender;

    private String name;

    private String description;

    private int oneStarRating;

    private int twoStarRating;

    private int threeStarRating;

    private int fourStarRating;

    private int fiveStarRating;

    private double averageRating;

    private double price;

    private int year;

    @OneToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private SportEntity sport;

    @OneToOne
    @JoinColumn(name = "upper_id", referencedColumnName = "id")
    private UpperEntity upper;

    @OneToOne
    @JoinColumn(name = "midsole_id", referencedColumnName = "id")
    private MidsoleEntity midsole;

    @ManyToMany
    @JoinTable(name = "product_collection", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "collection_id"})})
    private Set<CollectionEntity> collections;

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "category_id"})})
    private Set<CategoryEntity> categories;

    @OneToMany(mappedBy = "product")
    private Set<ProductSizeEntity> sizes;
}
