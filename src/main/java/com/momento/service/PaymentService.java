//package com.momento.service;
//
//
//import com.momento.entity.PayMent;
//import com.momento.domain.reservation.dto.PostReservationReq;
//import com.siot.IamportRestClient.exception.IamportResponseException;
//import com.siot.IamportRestClient.response.IamportResponse;
//import com.siot.IamportRestClient.response.Payment;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//
//@Service
//public class PaymentService {
//
//    private static final String API_KEY = "1112686546320382";
//    private static final String API_SECRET = "1112686546320382";
//
//    public PaymentRes createPayment(PaymentReq paymentReq)
//            throws IamportResponseException, IOException {
//        //DB에 imp_uid나 merchant_uid가 중복되었는지 확인
//        checkDuplicatePayment(paymentReq);
//
//        //DB에 있는 금액과 사용자가 결제한 금액이 같은지 확인
//        int amountForPay = fromDB; //db에서 가져온 금액
//        IamportResponse<Payment> iamResponse = iamportClient.paymentByImpUid(paymentReq.getImpUid());
//        int paidAmount = iamResponse.getResponse().getAmount(); //사용자가 결제한 금액
//
//
//        return PaymentRes(new PaymentRes(payment.getId()));
//    }
//
//
//
//
//}