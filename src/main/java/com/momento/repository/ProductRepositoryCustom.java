package com.momento.repository;

import com.momento.dto.MainProductDto;
import com.momento.dto.ProductSearchDto;
import com.momento.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable);

    Page<MainProductDto> getMainProductPage(ProductSearchDto productSearchDto, Pageable pageable);
}