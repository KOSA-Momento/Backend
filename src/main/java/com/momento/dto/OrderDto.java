package com.momento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long orderId; // 주문 ID (숫자 형식)

    // 기타 주문과 관련된 필드들을 필요에 따라 추가할 수 있음

    public OrderDto(Long orderId) {
        this.orderId = orderId;
    }

    // 생성자, 게터, 세터, toString 등 필요한 메서드 추가
}
