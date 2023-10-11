//package com.momento.controller;
//
//import com.momento.entity.Order;
//import com.momento.service.PaymentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import retrofit2.Response;
//
//@RestController
//@RequiredArgsConstructor
//public class PaymentController implements PaymentService {
//    private final Order order;
//
//    @PostMapping("/payment/validate")
//    public Response<PaymentRes> createPayment(@RequestBody PaymentReq paymentReq) {
//
//        PaymentRes paymentRes = orderService.createOrder(paymentReq);
//        return new Response<>(paymentRes);
//
//    }
//
//}

//https://github.com/blackarea/shimpyo/blob/master/src/main/java/com/oneline/shimpyo/service/PaymentService.java