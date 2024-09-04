package com.variate.services;

import com.variate.model.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto getPaymentById(Long id);
    List<PaymentDto> getAllPayments();
    PaymentDto updatePayment(Long id, PaymentDto paymentDto);
    PaymentDto patchPayment(Long id, PaymentDto paymentDto);
    void deletePayment(Long id);
}
