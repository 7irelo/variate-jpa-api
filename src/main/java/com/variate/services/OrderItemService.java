package com.variate.services;

import com.variate.model.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);
    OrderItemDto getOrderItemById(Long id);
    List<OrderItemDto> getAllOrderItems();
    OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);
    OrderItemDto patchOrderItem(Long id, OrderItemDto orderItemDto);
    void deleteOrderItem(Long id);
}
