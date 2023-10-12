package com.momento.entity;

import com.momento.dto.ProductFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;

@Entity
@Table(name = "Products")
@Getter
@Setter
@ToString
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "b4_title")
    private String b4Title;

    @Column(name = "b4_description", length = 2000)
    private String b4Description;

    //    @ManyToOne
//    @JoinColumn(name = "b4_seller_id")
//    private Member b4SellerId;
    @Column(name = "b4_seller_id")
    private String b4SellerId;

    @Column(name = "b4_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime b4Date;

    @Column(name = "b4_insta_id")
    private String b4InstaId;

    @Column(name = "b4_price")
    private Integer b4Price;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;

    @Column(name = "price")
    private Integer price;

    @Column(name = "theme")
    private String theme;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<LikeItem> likeItems;


    public void updateProduct(ProductFormDto productFormDto){
        this.id = productFormDto.getId();
        this.b4Title = productFormDto.getB4Title();
        this.b4Description = productFormDto.getB4Description();
        this.b4SellerId = productFormDto.getB4SellerId();
        this.b4Date = LocalDateTime.parse(productFormDto.getB4Date());
        this.b4InstaId = productFormDto.getB4InstaId();
        this.b4Price = productFormDto.getB4Price();
        this.title = productFormDto.getTitle();
        this.description = productFormDto.getDescription();
        this.date = LocalDateTime.parse(productFormDto.getDate());
        this.price = productFormDto.getPrice();
        this.theme = productFormDto.getTheme();
    }
}