package com.momento.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter @Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private String imgName;

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl;

    private String repimgYn; //대표 이미지 여부


    public void updateImage(String oriImgName, String imgName, String imageUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.setImgUrl(imageUrl);
    }
}