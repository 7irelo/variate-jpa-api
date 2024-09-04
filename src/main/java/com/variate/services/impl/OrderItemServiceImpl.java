package com.variate.services.impl;

import com.variate.mappers.impl.OrderItemMapper;
import com.variate.model.dto.OrderItemDto;
import com.variate.repositories.OrderItemRepository;
import com.variate.services.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        var orderItem = orderItemMapper.mapFrom(orderItemDto);
        return orderItemMapper.mapTo(orderItemRepository.save(orderItem));
    }

    @Override
    public OrderItemDto getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(orderItemMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(orderItemMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto) {
        var orderItem = orderItemMapper.mapFrom(orderItemDto);
        orderItem.setId(id);
        return orderItemMapper.mapTo(orderItemRepository.save(orderItem));
    }

    @Override
    public OrderItemDto patchOrderItem(Long id, OrderItemDto orderItemDto) {
        var existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
        if (orderItemDto.getQuantity() != null) {
            existingOrderItem.setQuantity(orderItemDto.getQuantity());
        }
        if (orderItemDto.getUnitPrice() != null) {
            existingOrderItem.setUnitPrice(orderItemDto.getUnitPrice());
        }
        return orderItemMapper.mapTo(orderItemRepository.save(existingOrderItem));
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
