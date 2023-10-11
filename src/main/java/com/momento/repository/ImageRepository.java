package com.momento.repository;

import com.momento.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    //    List<Image> findByProductImgUrlIdProductIdOrderByProductImgUrlIdProductIdAsc(Integer productId);

    List<Image> findByProductIdOrderById(Long productId);

    Image findByProductIdAndRepimgYn(Long itemId, String repimgYn);
}
