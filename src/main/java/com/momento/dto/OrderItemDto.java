package com.momento.dto;

import com.momento.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderItemDto {

    public OrderItemDto(OrderItem orderItem, String image) {
        this.title = orderItem.getProduct().getTitle();
        this.orderPrice = orderItem.getOrderPrice();
        this.image = image;
    }

    private String title;
    private int orderPrice;
    private String image;


}




