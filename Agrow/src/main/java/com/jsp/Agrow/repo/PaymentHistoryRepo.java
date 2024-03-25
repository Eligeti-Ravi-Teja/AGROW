package com.jsp.Agrow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agrow.entity.PaymentHistory;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Integer>{

}
