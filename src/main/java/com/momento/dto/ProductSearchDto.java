package com.momento.dto;

//import com.momento.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearchDto {

    private String searchDateType;

//    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";

}