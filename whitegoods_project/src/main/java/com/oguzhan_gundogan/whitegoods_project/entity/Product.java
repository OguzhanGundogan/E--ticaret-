package com.oguzhan_gundogan.whitegoods_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double price;

    @Column(name = "units_in_stock")
    private Long unitsInStock;

    @Column(name = "category_id")
    private Long categoryId;

    private Boolean active;

    @Column(name = "product_image_url")
    private String image;
    //TODO: yorumlar - puanlama
    //TODO: ürün özellikleri
}