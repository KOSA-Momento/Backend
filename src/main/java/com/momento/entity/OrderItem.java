package com.momento.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OrderItems")
public class OrderItem implements Serializable {

    @Id
    private int ordersId;

    @Id
    private int productsId;

    @ManyToOne
    @JoinColumn(name = "ordersId", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productsId", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

}