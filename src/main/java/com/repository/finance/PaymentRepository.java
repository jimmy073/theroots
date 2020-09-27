package com.repository.finance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.finance.Payment;
import com.domain.people.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByPaymentId(String paymentId);
	
	List<Payment> findByPaidBy(User user);
	
}
