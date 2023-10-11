package com.momento.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter @Setter
//@IdClass(ProductImgId.class)
public class Image {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    private String contentUrl;
    private String originalImageName; //원본 이미지 파일명



    public void updateImage(String originalImageName, String imageUrl) {
        this.originalImageName = originalImageName;
        this.setContentUrl(imageUrl);
    }

    public String getImgName() {
        var result = this.getContentUrl();
        return result.substring("/images/product/".length());
    }
}