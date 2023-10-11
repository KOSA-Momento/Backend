package com.momento.controller;

import com.momento.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.momento.dto.ProductFormDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityNotFoundException;

import com.momento.dto.ProductSearchDto;
import com.momento.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/user/product/new")
    public String b4Product(Model model) {
        model.addAttribute("productFormDto", new ProductFormDto());
        return "product/b4ProductForm";
    }

    @GetMapping(value = "/admin/product/new")
    public String product(Model model) {
        model.addAttribute("productFormDto", new ProductFormDto());
        return "product/ProductForm";
    }


    @PostMapping("/user/product/new")
    public String b4Product(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
                            Model model, @RequestParam("imageFile") List<MultipartFile> imageFileList) {

        if (bindingResult.hasErrors()) {
            return "b4ProductForm";
        }

        if (imageFileList.get(0).isEmpty() && productFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "b4ProductForm";
        }

        try {
            productService.saveProduct(productFormDto, imageFileList);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "b4ProductForm";
        }

        return "redirect:/";
    }


    @PostMapping("/admin/product/new")
    public String product(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("imageFile") List<MultipartFile> imageFileList) {

        if (bindingResult.hasErrors()) {
            return "b4ProductForm";
        }

        if (imageFileList.get(0).isEmpty() && productFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "b4ProductForm";
        }

        try {
            productService.saveProduct(productFormDto, imageFileList);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "b4ProductForm";
        }

        return "redirect:/";
    }


    @GetMapping(value = "/user/product/{productId}")
    public String b4Product(@PathVariable("productId") Long productId, Model model) {
        try {
            ProductFormDto productFormDto = productService.getProductDtl(productId);
            model.addAttribute("productFormDto", productFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("productFormDto", new ProductFormDto());
        }

        return "product/ProductForm"; // ProductForm 템플릿을 반환
    }

    @GetMapping(value = "/admin/product/{productId}")
    public String product(@PathVariable("productId") Long productId, Model model) {
        try {
            ProductFormDto productFormDto = productService.getProductDtl(productId);
            model.addAttribute("productFormDto", productFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("productFormDto", new ProductFormDto());
        }

        return "product/ProductForm"; // editProductForm 템플릿을 반환
    }

    @PostMapping(value = "/user/product/{productId}")
    public String b4ProductUpdate(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
                                  @RequestParam("imageFile") List<MultipartFile> imageFileList, Model model){
        if (bindingResult.hasErrors()){
            return "b4ProductForm";
        }

        if (imageFileList.get(0).isEmpty() && productFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "b4ProductForm";
        }

        try {
            productService.updateProduct(productFormDto, imageFileList);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "b4ProductForm";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/admin/product/{productId}")
    public String productUpdate(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
                                @RequestParam("imageFile") List<MultipartFile> imageFileList, Model model){
        if (bindingResult.hasErrors()){
            return "b4ProductForm";
        }

        if (imageFileList.get(0).isEmpty() && productFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "b4ProductForm";
        }

        try {
            productService.updateProduct(productFormDto, imageFileList);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "b4ProductForm";
        }

        return "redirect:/";
    }


    @GetMapping(value = {"/admin/products", "/admin/products/{page}"})
    public String productManage(ProductSearchDto productSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        var pageable = PageRequest.of(page.orElse(0), 3);
        var products = productService.getAdminProductPage(productSearchDto, pageable);

        model.addAttribute("products", products);
        model.addAttribute("productSearchDto", productSearchDto);
        model.addAttribute("maxPage", 5);

        return "product/productMng";
    }

}