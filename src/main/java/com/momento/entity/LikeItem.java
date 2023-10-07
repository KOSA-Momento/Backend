package com.momento.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LikeItems")
public class LikeItem implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "membersId", referencedColumnName = "id", insertable = false, updatable = false)
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "productsId", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    // Getter와 Setter 메서드 생략
}
