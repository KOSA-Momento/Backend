package com.momento.repository;

import com.momento.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    // 특별한 쿼리 메서드나 사용자 정의 메서드가 필요한 경우 여기에 추가할 수 있습니다.
}