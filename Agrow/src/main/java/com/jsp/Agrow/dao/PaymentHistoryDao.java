package com.jsp.Agrow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agrow.entity.PaymentHistory;
import com.jsp.Agrow.repo.PaymentHistoryRepo;

@Repository
public class PaymentHistoryDao {
	@Autowired
	PaymentHistoryRepo dao;
	
//	=====================save payment=============================================
	public PaymentHistory savePayment(PaymentHistory history) {
		return dao.save(history);
	}
}
