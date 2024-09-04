package com.variate.services.impl;

import com.variate.mappers.impl.OrderMapper;
import com.variate.model.dto.OrderDto;
import com.variate.repositories.OrderRepository;
import com.variate.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        var order = orderMapper.mapFrom(orderDto);
        return orderMapper.mapTo(orderRepository.save(order));
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        var order = orderMapper.mapFrom(orderDto);
        order.setId(id);
        return orderMapper.mapTo(orderRepository.save(order));
    }

    @Override
    public OrderDto patchOrder(Long id, OrderDto orderDto) {
        var existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (orderDto.getStatus() != null) {
            existingOrder.setStatus(orderDto.getStatus());
        }
        if (orderDto.getTotalCost() != null) {
            existingOrder.setTotalCost(orderDto.getTotalCost());
        }
        return orderMapper.mapTo(orderRepository.save(existingOrder));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
