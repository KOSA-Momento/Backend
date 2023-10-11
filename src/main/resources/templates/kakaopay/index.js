


    let index = {
	init:function(){
        $("#btn-kakaopay").on("click", ()=>{ // function(){}를 사용안하고 , ()=>{}를 사용하는 이유는 this를 바인딩하기 위해서
			this.kakaopay();
		});
	},

  // 카카오페이 결제
	kakaopay:function(){

		$.ajax({
			url:"kakaopay",
			dataType:"json"
		}).done(function(resp){
			if(resp.status === 500){
				alert("카카오페이결제를 실패하였습니다.")
			} else{
				 // alert(resp.tid); //결제 고유 번호
				var box = resp.next_redirect_pc_url;
				//window.open(box); // 새창 열기
				location.href = box;
			}

		}).fail(function(error){
			alert(JSON.stringify(error));
		});

	},
}

index.init();













<!--<!DOCTYPE html>-->
<!--<html lang="en">-->
<!--<head>-->
<!--    &lt;!&ndash; jQuery &ndash;&gt;-->
<!--    <script-->
<!--            type="text/javascript"-->
<!--            src="https://code.jquery.com/jquery-1.12.4.min.js"-->
<!--    ></script>-->
<!--    &lt;!&ndash; iamport.payment.js &ndash;&gt;-->
<!--    <script-->
<!--            type="text/javascript"-->
<!--            src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"-->
<!--    ></script>-->
<!--    <script>-->
<!--        var IMP = window.IMP;-->
<!--        IMP.init("imp80786406");-->

<!--        function requestPay() {-->
<!--            IMP.request_pay(-->
<!--                {-->
<!--                    pg: "kakaopay",		//KG이니시스 pg파라미터 값-->
<!--                    pay_method: "kakaopay",		//결제 방법-->
<!--                    merchant_uid: "1234578",//주문번호-->
<!--                    name: "당근 10kg",		//상품 명-->
<!--                    amount: 200,			//금액-->
<!--      				buyer_email: "gildong@gmail.com",-->
<!--      				buyer_name: "홍길동",-->
<!--      				buyer_tel: "010-4242-4242",-->
<!--      				buyer_addr: "서울특별시 강남구 신사동",-->
<!--      				buyer_postcode: "01181"-->

<!--                },-->
<!--                function (rsp) {-->
<!--      				//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.-->
<!--                    if (rsp.success) {-->
<!--                        //서버 검증 요청 부분-->
<!--                        $.ajax({-->
<!--                            url: "/payment/validate/" + rsp.imp_uid,-->
<!--                            method: "GET",-->
<!--                            contentType: "application/json",-->
<!--                            data: JSON.stringify({-->
<!--                                imp_uid: rsp.imp_uid,            // 결제 고유번호-->
<!--                                merchant_uid: rsp.merchant_uid,   // 주문번호-->
<!--                                amount: rsp.paid_amount-->
<!--                            }),-->
<!--                        }).done(function (data) {-->
<!--                            // 가맹점 서버 결제 API 성공시 로직-->
<!--                    } else {-->
<!--                        alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);-->
<!--                    }-->
<!--                }-->
<!--            );-->
<!--        }-->
<!--    </script>-->
<!--    <meta charset="UTF-8"/>-->
<!--    <title>Sample Payment</title>-->
<!--</head>-->
<!--<body>-->
<!--<button onclick="requestPay()">결제하기</button>-->
<!--&lt;!&ndash; 결제하기 버튼 생성 &ndash;&gt;-->
<!--</body>-->
<!--</html>-->
