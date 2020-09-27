package com.service.finance;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.finance.Payment;
import com.domain.people.User;

@Service
public interface PaymentService {

	Payment savePayment(Payment payment);
	
	Payment findPayment(String paymentId);
	
	Payment findPayment(Long id);
	
	List<Payment> listOfPayments();
	
	List<Payment> listOfPaymentsOfUser(User user);
	
	List<Payment> listOfPaymentsOfMode(String paymentMode);
	
	List<Payment> listOfPaymentsOfType(String paymentType);
	
	
}
