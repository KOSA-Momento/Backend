package com.momento.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KPReadyResponse {
    private String tid;
    private String next_redirect_pc_url;
    private String partner_order_id;
}
