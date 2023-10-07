package com.momento.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderItemsId", referencedColumnName = "id", insertable = false, updatable = false)
    private OrderItem orderItem;

    @Id
    @ManyToOne
    @JoinColumn(name = "productsId", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

}
