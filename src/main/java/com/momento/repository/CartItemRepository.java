package com.momento.repository;

import com.momento.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

//import com.momento.dto.CartDetailDto;
////import org.springframework.data.jpa.repository.Query;
//import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndProductId(Long cartId, Long productId);

//    List<CartDetailDto> findCartDetailDtoList(Long cartId);

//    @Query("select new com.momento.dto.CartDetailDto(ci.id, p.title, p.price, im.contentUrl) " +
//            "from CartItem ci, Image im " +
//            "join ci.product p " +
//            "where ci.cart.id = :cartId " +
//            "and im.product.id = ci.product.id " +
////            "and im.repimgYn = 'Y' " +
//            "order by ci.b4Date desc"
//    )
}
