package com.momento.service;

import com.momento.dto.ProductFormDto;
import com.momento.entity.Image;
import com.momento.entity.Product;
import com.momento.repository.ImageRepository;
import com.momento.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.momento.dto.ImageDto;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public Long saveProduct(ProductFormDto productFormDto, List<MultipartFile> imageFileList) throws Exception {

        //상품 등록
        Product product = productFormDto.createProduct();
        productRepository.save(product);

        //이미지 등록
        for(int i=0;i<imageFileList.size();i++){
            Image image = new Image();
            image.setProduct(product);

            imageService.saveImage(image, imageFileList.get(i));
        }

        return product.getId();
    }

    @Transactional(readOnly = true)
    public ProductFormDto getProductDtl(Long productId) {
        List<Image> imageList = imageRepository.findByProductIdOrderByContentUrlDesc(productId);
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (Image image : imageList) {
            ImageDto imageDto = ImageDto.of(image);
            imageDtoList.add(imageDto);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);
        ProductFormDto productFormDto = ProductFormDto.of(product);
        productFormDto.setImageDtoList(imageDtoList);
        return productFormDto;
    }


    public Long updateProduct(ProductFormDto productFormDto, List<MultipartFile> imageFileList) throws Exception {
        //상품 수정
        Product product = productRepository.findById(productFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        product.updateProduct(productFormDto);
        List<Integer> imageIds = productFormDto.getImageIds();

        //이미지 등록
        for(int i=0;i<imageFileList.size();i++){
            imageService.updateImage(Long.valueOf(imageIds.get(i)),
                    imageFileList.get(i));
        }

        return product.getId();
    }



}