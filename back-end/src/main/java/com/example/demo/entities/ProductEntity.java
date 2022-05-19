package com.example.demo.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private SportEntity sport;

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_technology", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private Set<TechnologyEntity> technologies;

    @OneToMany(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<ProductSizeEntity> sizes;

    @OneToMany(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<UserRateProductEntity> reviews;

    private boolean isDeleted = false;

    private String thumbnail;

    private Integer countRating = 0;

    public Integer getCountRating() {
        return countRating == null ? 0 : countRating;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProductEntity)) {
            return false;
        }

        ProductEntity product = (ProductEntity) object;

        return Objects.equals(this.id, product.getId()) &&
                this.name.equals(product.getName()) &&
                this.description.equals(product.getDescription()) &&
                this.price == product.getPrice() &&
                this.year == product.getYear() &&
                this.thumbnail.equals(product.getThumbnail());
    }
}
