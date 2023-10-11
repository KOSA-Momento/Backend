package com.momento.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class OrderDto {
    @NotNull
    private Long itemId;
    // 생성자, 게터, 세터, toString 등 필요한 메서드 추가
}
