package com.momento.repository;

import com.momento.entity.Image;
import com.momento.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Product> {
  
    List<Image> findByProductIdOrderByContentUrlDesc(Long productId);
}
