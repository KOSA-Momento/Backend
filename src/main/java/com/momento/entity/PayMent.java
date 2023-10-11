//package com.momento.entity;
//
//import lombok.*;
//import com.momento.method.PayMethod;
//import com.momento.constant.PayStatus;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//import static lombok.AccessLevel.PROTECTED;
//@Entity
//@Builder
//@Getter @Setter
//@AllArgsConstructor
//@NoArgsConstructor(access = PROTECTED)
//@Table(name = "PAYMENT")
//public class PayMent {
//
//    @Id @GeneratedValue
//    @Column(name = "pay_id")
//    private Long id;
//
//    @NotNull
//    private String UUID;
//
//    @NotNull
//    private String impUid;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private PayStatus payStatus;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private PayMethod payMethod;
//
//    @NotNull
//    private int price;
//
//    private int remainPrice;
//
//
//}
