package com.momento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDetailDto {

    private Long cartItemId;

    private String title;

    private int price;

    private String contentUrl;      //상품 이미지 경로

    public CartDetailDto(Long cartItemId, String title, int price, String contentUrl) {

        this.cartItemId = cartItemId;
        this.title = title;
        this.price = price;
        this.contentUrl = contentUrl;
    }
}
