package com.momento.service;

import com.momento.dto.KPReadyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KakaoPayService {

    @Autowired
    private CartMapper cartMapper;

    public KPReadyResponse payReady(int totalAmount) {

        User user =  (User)SessionUtils.getAttribute("LOGIN_USER");
        List<CartDto> carts = cartMapper.getCartByUserNo(user.getNo());

        String[] cartNames = new String[carts.size()];
        for(CartDto cart: carts) {
            for(int i=0; i< carts.size(); i++) {
                cartNames[i] = cart.getClassTitle();
            }
        }
        String itemName = cartNames[0] + " 그외" + (carts.size()-1);
        log.info("강좌이름들:"+itemName);
        String order_id = user.getNo() + itemName;

        // 카카오가 요구한 결제요청request값을 담아줍니다.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", order_id);
        parameters.add("partner_user_id", "inflearn");
        parameters.add("item_name", itemName);
        parameters.add("quantity", String.valueOf(carts.size()));
        parameters.add("total_amount", String.valueOf(totalAmount));
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost/order/pay/completed"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost/order/pay/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost/order/pay/fail"); // 결제 실패시 넘어갈 url

        log.info("파트너주문아이디:"+ parameters.get("partner_order_id")) ;
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        // 외부url요청 통로 열기.
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        // template으로 값을 보내고 받아온 ReadyResponse값 readyResponse에 저장.
        ReadyResponse readyResponse = template.postForObject(url, requestEntity, ReadyResponse.class);
        log.info("결재준비 응답객체: " + readyResponse);
        // 받아온 값 return
        return readyResponse;
    }

    // 결제 승인요청 메서드
    public ApproveResponse payApprove(String tid, String pgToken) {
        User user =  (User)SessionUtils.getAttribute("LOGIN_USER");
        List<CartDto> carts = cartMapper.getCartByUserNo(user.getNo());
        // 주문명 만들기.
        String[] cartNames = new String[carts.size()];
        for(CartDto cart: carts) {
            for(int i=0; i< carts.size(); i++) {
                cartNames[i] = cart.getClassTitle();
            }
        }
        String itemName = cartNames[0] + " 그외" + (carts.size()-1);

        String order_id = user.getNo() + itemName;

        // request값 담기.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", tid);
        parameters.add("partner_order_id", order_id); // 주문명
        parameters.add("partner_user_id", "회사명");
        parameters.add("pg_token", pgToken);

        // 하나의 map안에 header와 parameter값을 담아줌.
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부url 통신
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.
        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        log.info("결재승인 응답객체: " + approveResponse);

        return approveResponse;
    }
    // header() 셋팅
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 어드민키");
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
