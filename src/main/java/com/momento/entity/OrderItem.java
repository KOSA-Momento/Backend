package com.momento.entity;

import com.momento.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OrderItems")
@Getter
@Setter
public class OrderItem {

    @Id
    private int id;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(name = "total_price", nullable = false)
    private String totalPrice;

    @Column(name = "completed", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "Members_id")
    private Member member;

    @OneToMany(mappedBy = "orderItem")
    private List<Order> orders;


}