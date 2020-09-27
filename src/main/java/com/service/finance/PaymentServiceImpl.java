package com.service.finance;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.finance.Payment;
import com.domain.people.User;
import com.repository.finance.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private PaymentRepository paymentRepository;

	@Autowired	
	public void setPaymentRepository(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment findPayment(String paymentId) {
		return paymentRepository.findByPaymentId(paymentId);
	}

	@Override
	public Payment findPayment(Long id) {
		Optional<Payment> payment = this.paymentRepository.findById(id);
        if (payment.isPresent())
            return payment.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public List<Payment> listOfPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public List<Payment> listOfPaymentsOfUser(User user) {
		return paymentRepository.findByPaidBy(user);
	}

	@Override
	public List<Payment> listOfPaymentsOfMode(String paymentMode) {
		
		return null;
	}

	@Override
	public List<Payment> listOfPaymentsOfType(String paymentType) {
		// TODO Auto-generated method stub
		return null;
	}

}
