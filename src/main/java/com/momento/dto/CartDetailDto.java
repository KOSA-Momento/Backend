package com.momento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDto {

    private Long cartItemId;

    private String title;

    private int price;

    private String imgUrl;      //상품 이미지 경로

    public CartDetailDto(Long cartItemId, String title, int price, String imgUrl) {

        this.cartItemId = cartItemId;
        this.title = title;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
