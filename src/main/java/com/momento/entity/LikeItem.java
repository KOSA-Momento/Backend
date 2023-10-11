package com.momento.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LikeItems")
public class LikeItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "members_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

}
