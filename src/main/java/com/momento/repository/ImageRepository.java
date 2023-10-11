package com.momento.repository;

import com.momento.entity.Image;
import com.momento.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProductIdOrderById(Long productId);

    Image findByProductIdAndRepimgYn(Long itemId, String repimgYn);
}
