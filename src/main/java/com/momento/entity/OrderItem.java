package com.momento.entity;

import com.momento.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OrderItems")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    public static OrderItem createOrderItem(Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrderPrice(product.getPrice());
        return orderItem;
    }

    public int getTotalPrice() {
        return orderPrice;
    }

}