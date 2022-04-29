package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductEntity extends Auditable {
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

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id")
    private CollectionEntity collection;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

//    @OneToMany(mappedBy = "product")
//    private Set<ProductSizeEntity> sizes;

    private boolean isDeleted = false;
}
