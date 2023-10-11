package com.momento.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.momento.dto.ProductFormDto;
import com.momento.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityNotFoundException;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/admin/product/new")
    public String productForm(Model model) {
        model.addAttribute("productFormDto", new ProductFormDto());
        return "product/productForm";
    }

    @PostMapping("/admin/product/new")
    public String productNew(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
                             Model model, @RequestParam("imageFile") List<MultipartFile> imageFileList) {

        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }

        if (imageFileList.get(0).isEmpty() && productFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "product/productForm";
        }

        try {
            productService.saveProduct(productFormDto, imageFileList);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "product/productForm";
        }

        return "redirect:/";
    }


    @GetMapping(value = "/admin/product/{productId}")
    public String productDtl(@PathVariable("productId") Long productId, Model model){

        try {
            ProductFormDto productFormDto = productService.getProductDtl((long) Math.toIntExact(productId));
            model.addAttribute("productFormDto", productFormDto);
        } catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("productFormDto", new ProductFormDto());
            return "product/productForm";
        }

        return "product/productForm";
    }

    @PostMapping(value = "/admin/product/{productId}")
    public String productUpdate(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
                                @RequestParam("imageFile") List<MultipartFile> imageFileList, Model model){
        if(bindingResult.hasErrors()){
            return "product/productForm";
        }

        if(imageFileList.get(0).isEmpty() && productFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "product/productForm";
        }

        try {
            productService.updateProduct(productFormDto, imageFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "product/productForm";
        }

        return "redirect:/";
    }

}
