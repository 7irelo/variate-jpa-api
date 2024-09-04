package com.variate.services.impl;

import com.variate.mappers.impl.PaymentMapper;
import com.variate.model.dto.PaymentDto;
import com.variate.repositories.PaymentRepository;
import com.variate.services.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        var payment = paymentMapper.mapFrom(paymentDto);
        return paymentMapper.mapTo(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto updatePayment(Long id, PaymentDto paymentDto) {
        var payment = paymentMapper.mapFrom(paymentDto);
        payment.setId(id);
        return paymentMapper.mapTo(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto patchPayment(Long id, PaymentDto paymentDto) {
        var existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        if (paymentDto.getAmount() != null) {
            existingPayment.setAmount(paymentDto.getAmount());
        }
        if (paymentDto.getPaymentMethod() != null) {
            existingPayment.setPaymentMethod(paymentDto.getPaymentMethod());
        }
        return paymentMapper.mapTo(paymentRepository.save(existingPayment));
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
