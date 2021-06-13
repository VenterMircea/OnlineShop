package venter.mircea.onlinestore.utils;

import venter.mircea.onlinestore.model.dto.CartDto;
import venter.mircea.onlinestore.model.dto.CartDtoNoId;
import venter.mircea.onlinestore.model.entity.CartEntity;

import java.util.Map;

public class CartTestUtils {
    public static CartEntity createCartEntity(String id, String userId,
                                              Map<String, Integer> cartProducts) {

        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(id);
        cartEntity.setUserId(userId);
        cartEntity.setProducts(cartProducts);
        return cartEntity;
    }

    public static CartDto createCartDto(String id, String userId, Map<String, Integer> products) {

        CartDto cartDto = new CartDto();
        cartDto.setId(id);
        cartDto.setUserId(userId);
        cartDto.setProducts(products);
        return cartDto;
    }

    public static CartDtoNoId createCartDtoNoId(String userId, Map<String, Integer> products) {

        CartDtoNoId cartDtoNoId = new CartDtoNoId();
        cartDtoNoId.setUserId(userId);
        cartDtoNoId.setProducts(products);
        return cartDtoNoId;
    }

}
