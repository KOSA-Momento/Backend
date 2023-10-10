package com.momento.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "CartItems")
@Getter @Setter
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Product product;

//    public static CartItem createCartItem(Member member, Product product) {
//        CartItem cartItem = new CartItem();
//        cartItem.setMember(member); //해당 회원 장바구니 생성
//        cartItem.setProduct(product); //장바구니에 담을 상품 엔티티를 생성하는 메소드
//        return cartItem;
//    }

}