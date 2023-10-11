package com.momento.dto;

import com.momento.constant.OrderStatus;
import com.momento.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class OrderHistDto {
    public OrderHistDto(Order order){
        this.orderId = order.getOrderId();
        FormatStyle formatStyle = FormatStyle.valueOf("yyyy-MM-dd HH:mm");
        this.orderDate = LocalDateTime.parse(order.getOrderDate().format(DateTimeFormatter.ofLocalizedDateTime(formatStyle))); //이렇게 길어야하나...
        this.orderStatus = order.getOrderStatus();

    }
    private Long orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
}
