//package com.momento.controller;
//
//import com.momento.config.auth.PrincipalDetails;
//import com.momento.entity.user.User;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//public class KakaoChargeController {
//    @Transactional
//    @GetMapping("/user/cash/{id}")
//    public String charge(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        // 로그인 유저와 잔액 충전 페이지에 접속하는 id가 같아야 함.
//        if(principalDetails.getUser().getId() == id){
//            // 유저의 주문내역
//            User user = userPageService.findUser(id);
//            model.addAttribute("user",user);
//
//            return "/user/cash";
//        }else{
//            return "redirect:/main";
//        }
//    }
//
//    // 잔액충전 처리
//    @GetMapping("/user/charge/pro")
//    public String chargePro(int amount, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        User user = userPageService.findUser(principalDetails.getUser().getId());
//        userPageService.chargePoint(user.getId(),amount);
//        return "redirect:/main";
//    }
//}
