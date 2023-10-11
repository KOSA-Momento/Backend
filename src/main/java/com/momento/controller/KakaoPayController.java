//package com.momento.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//@Slf4j
//@Controller
//// 세션에 저장된 겂을 사용할때 쓰는 어노테이션, session에서 없으면 model까지 훑어서 찾아냄.
//@SessionAttributes({"tid","order"})
//public class KakaoPayController {
//
//
//    // 카카오페이결제 요청
//    @GetMapping("/order/pay")
//    public @ResponseBody ReadyResponse payReady(@RequestParam(name = "total_amount") int totalAmount, Order order, Model model) {
//
//        log.info("주문정보:"+order);
//        log.info("주문가격:"+totalAmount);
//        // 카카오 결제 준비하기	- 결제요청 service 실행.
//        ReadyResponse readyResponse = kakaopayService.payReady(totalAmount);
//        // 요청처리후 받아온 결재고유 번호(tid)를 모델에 저장
//        model.addAttribute("tid", readyResponse.getTid());
//        log.info("결재고유 번호: " + readyResponse.getTid());
//        // Order정보를 모델에 저장
//        model.addAttribute("order",order);
//
//        return readyResponse; // 클라이언트에 보냄.(tid,next_redirect_pc_url이 담겨있음.)
//    }
//
//    // 결제승인요청
//    @GetMapping("/order/pay/completed")
//    public String payCompleted(@RequestParam("pg_token") String pgToken, @ModelAttribute("tid") String tid, @ModelAttribute("order") Order order,  Model model) {
//
//        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
//        log.info("주문정보: " + order);
//        log.info("결재고유 번호: " + tid);
//
//        // 카카오 결재 요청하기
//        ApproveResponse approveResponse = kakaopayService.payApprove(tid, pgToken);
//
//        // 5. payment 저장
//        //	orderNo, payMathod, 주문명.
//        // - 카카오 페이로 넘겨받은 결재정보값을 저장.
//        Payment payment = Payment.builder()
//                .paymentClassName(approveResponse.getItem_name())
//                .payMathod(approveResponse.getPayment_method_type())
//                .payCode(tid)
//                .build();
//
//        orderService.saveOrder(order,payment);
//
//        return "redirect:/orders";
//    }
//    // 결제 취소시 실행 url
//    @GetMapping("/order/pay/cancel")
//    public String payCancel() {
//        return "redirect:/carts";
//    }
//
//    // 결제 실패시 실행 url
//    @GetMapping("/order/pay/fail")
//    public String payFail() {
//        return "redirect:/carts";
//    }
//
//
//}