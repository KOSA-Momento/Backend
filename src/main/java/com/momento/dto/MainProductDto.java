package com.momento.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainProductDto {

    private Long id;

    private String title;

    private String description;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainProductDto(Long id, String title, String description, String imgUrl, Integer price){
        this.id = id;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

}