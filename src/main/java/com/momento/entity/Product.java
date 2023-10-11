package com.momento.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "b4_title")
    private String b4Title;

    @Column(name = "b4_description", length = 2000)
    private String b4Description;

    @ManyToOne
    @JoinColumn(name = "b4_seller_id")
    private Member member;

    @Column(name = "b4_date")
    private LocalDateTime b4Date;

    @Column(name = "b4_insta_id")
    private String b4InstaId;

    @Column(name = "b4_price")
    private int b4Price;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "price")
    private int price;

    // 복합 pk로 만들 것이기 때문에 수정 필요
    @ManyToOne
    @JoinColumn(name = "Theme_id")
    private Theme theme;

    @OneToMany(mappedBy = "productContentId.product")
    private List<Image> images;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<LikeItem> likeItems;


}