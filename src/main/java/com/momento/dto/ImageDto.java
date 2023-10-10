package com.momento.dto;

import com.momento.entity.Image;
import com.momento.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ImageDto {

    private Long id;

    private Product product;

    private String originalImageName;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ImageDto of(Image image) {
        return modelMapper.map(image, ImageDto.class);
    }

}