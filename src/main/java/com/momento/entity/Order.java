package com.momento.entity;

import com.momento.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "members_id")
    private Member member;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_status")
    private OrderStatus orderStatus;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();


//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }



//    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
//        Order order = new Order();
//        order.setMember(member);
//
//        for(OrderItem orderItem : orderItemList) {
//            order.addOrderItem(orderItem);
//        }
//
//        order.setOrderStatus(OrderStatus.COMPLETED);
//        order.setOrderDate(LocalDateTime.now());
//        return order;
//    }

//    public int getTotalPrice() {
//        int totalPrice = 0;
//        for(OrderItem orderItem : orderItems){
//            totalPrice += orderItem.getTotalPrice();
//        }
//        return totalPrice;
//    }



}

