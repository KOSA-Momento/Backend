package com.momento.dto;

import com.momento.entity.Member;
import com.momento.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductFormDto {

    private Long id;

    @NotBlank(message = "매입상품명은 필수 입력 값입니다.")
    private String b4Title;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String b4Description;

//    @NotNull(message = "판매자아이디는 필수 입력 값입니다.")
//    private Member b4SellerId; // 필드 이름 변경

    @NotNull(message = "매입일은 필수 입력 값입니다.")
    private String b4Date;

    @NotNull(message = "인스타아이디는 필수 입력 값입니다.")
    private String b4InstaId;

    @NotNull(message = "매입가격은 필수 입력 값입니다.")
    private Integer b4Price;


    private String title;


    private String description;


    private String date;


    private Integer price;

    @NotNull(message = "테마아이디는 필수 입력 값입니다.")
    private String theme; // 필드 타입 변경

    private List<ImageDto> imageDtoList = new ArrayList<>();

    private List<Integer> imageIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        var convertedProduct = modelMapper.map(this, Product.class);
        convertedProduct.setB4Date(LocalDateTime.parse(this.getB4Date(), formatter));
        convertedProduct.setDate(LocalDateTime.parse(this.getDate(), formatter));

        return convertedProduct;
    }

    public static ProductFormDto of(Product product) {
        return modelMapper.map(product, ProductFormDto.class);
    }
}