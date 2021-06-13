package venter.mircea.onlinestore.service;

import venter.mircea.onlinestore.model.dto.CartDto;
import venter.mircea.onlinestore.model.dto.CartDtoNoId;

public interface CartService {

    CartDto getCartById(String id);

    CartDto getCartByUserId(String id);

    CartDto createCart(CartDtoNoId cartDtoNoId);

    CartDto updateCart(String id, CartDtoNoId cartDtoNoId);

    void deleteCart(String id);
}
