//package com.momento.repository;
//
//import com.momento.entity.PayMent;
//import com.siot.IamportRestClient.exception.IamportResponseException;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.io.IOException;
//import java.util.Optional;
//
//public interface PaymentRepository extends JpaRepository<PayMent, Long> {
//
//    @Query("select p from PayMent p where p.UUID = :UUID or p.impUid = :impUid")
//    Optional<PayMent> duplicateCheck(@Param("UUID") String UUID, @Param("impUid") String impUid);
//
//}