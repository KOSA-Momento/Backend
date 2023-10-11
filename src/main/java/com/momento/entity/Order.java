package com.momento.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    private int id;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(name = "total_price", nullable = false)
    private String totalPrice;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "Members_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    // Getter와 Setter 메서드 생략
}
