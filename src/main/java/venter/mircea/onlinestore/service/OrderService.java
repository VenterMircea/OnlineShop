package venter.mircea.onlinestore.service;

import venter.mircea.onlinestore.model.dto.OrderDto;
import venter.mircea.onlinestore.model.dto.OrderDtoNoId;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAll();

    OrderDto findById(String orderId);

    List<OrderDto> findByUser(String userId);

    OrderDto createOrder(OrderDtoNoId orderDtoNoId);

    OrderDto updateOrder(String id, OrderDtoNoId orderDtoNoId);

    void deleteOrder(String id);
}
