package com.app.basic.domain.entity;

//TBL_PRODUCT
//ID NUMBER PK
//PRODUCT_NAME VARCHAR2
//PRODUCT_PRICE NUMBER
//PRODUCT_BRAND VARCHAR2
//PRODUCT_STATUS VARCHAR2(SALE: 판매중, NOT_SALE: 판매안함)
//PRODUCT_RELEASE_DATE DATE

import com.app.basic.domain.type.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity @Table(name = "TBL_PRODUCT")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 255) // not null, varchar2(255)
    private String productName;
    @ColumnDefault("0")
    private Integer productPrice;
    @ColumnDefault("귀여운 강이")
    private String productBrand;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date productReleaseDate;
}
