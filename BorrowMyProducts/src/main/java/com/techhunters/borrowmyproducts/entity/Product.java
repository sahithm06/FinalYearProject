package com.techhunters.borrowmyproducts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ManyToOne//(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne//(cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_cost")
    private int productCost;

    @Column(name="quantity")
    private int quantity;

    @Column(name = "status")
    private String productStatus;
    @Lob
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductRequest> productRequests;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductLocation productLocation;


}
