package com.techhunters.borrowmyproducts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductRequest")
public class ProductRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_request_id")
    private int productRequestId;

    @Column(name = "status")
    private String status;

    @ManyToOne//(cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_uid")
    private User user;

    @ManyToOne//(cascade=CascadeType.MERGE)
    @JoinColumn(name = "product_pid")
    private Product product;

}
