package com.momento.entity;

//import com.momento.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.List;

@Entity
@Table(name = "OrderItems")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "order_price")
    private int orderPrice;

//    public static OrderItem createOrderItem(Product product) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setProduct(product);
//        orderItem.setOrderPrice(product.getPrice());
//        return orderItem;
//    }
//
//    public int getTotalPrice() {
//        return orderPrice;
//    }

}