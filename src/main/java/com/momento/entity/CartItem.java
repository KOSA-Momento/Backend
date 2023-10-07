package com.momento.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CartItems")
public class CartItem implements Serializable {



    @Id
    @ManyToOne
    @JoinColumn(name = "productsId", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "membersId", referencedColumnName = "id", insertable = false, updatable = false)
    private Member member;

    // Getter와 Setter 메서드 생략
}
