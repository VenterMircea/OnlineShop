package venter.mircea.onlinestore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import venter.mircea.onlinestore.model.dto.CartDto;
import venter.mircea.onlinestore.model.dto.CartDtoNoId;
import venter.mircea.onlinestore.service.implementation.CartServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import venter.mircea.onlinestore.utils.CartTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {CartController.class, CartServiceImpl.class})
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartServiceImpl cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    private ArgumentCaptor<CartDtoNoId> cartDtoNoIdArgumentCaptor;

    @Test
    public void testFindById() throws Exception {
        Map<String, Integer> productsToBuy = new HashMap<>();
        productsToBuy.put("blaba22", 3432);
        String cartId = "123456";
        CartDto cartDto = CartTestUtils.createCartDto(cartId, "testtest", productsToBuy);

        when(cartService.getCartById(cartDto.getId())).thenReturn(cartDto);
        mockMvc.perform(get("/carts/{id}", cartId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(cartDto.getId()));
        verify(cartService).getCartById(cartDto.getId());
    }

    @Test
    public void testFindByUserId() throws Exception {
        Map<String, Integer> productsToBuy = new HashMap<>();
        productsToBuy.put("blaba22", 3432);
        String userId = "testtest";
        String cartId = "1234567";
        CartDto cartDto = CartTestUtils.createCartDto(cartId, userId, productsToBuy);

        when(cartService.getCartByUserId(cartDto.getUserId())).thenReturn(cartDto);
        mockMvc.perform(get("/carts/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.userId").value(cartDto.getUserId()));
        verify(cartService).getCartByUserId(cartDto.getUserId());
    }

    @Test
    public void testCreateCart() throws Exception {
        Map<String, Integer> productsToBuy = new HashMap<>();
        productsToBuy.put("blaba22", 3432);
        String userId = "testtest";
        String cartId = "1234567";
        // GIVEN
        CartDtoNoId request = CartTestUtils.createCartDto(cartId,
                userId, productsToBuy);
        CartDtoNoId createdCart = CartTestUtils.createCartDto(cartId,
                userId, productsToBuy);
        doReturn(createdCart).when(cartService).createCart(any(CartDtoNoId.class));
        // WHEN
        mockMvc.perform(post("/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                // validate the status and response content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        verify(cartService).createCart(any(CartDtoNoId.class));
    }

    @Test
    void testUpdateCart() throws Exception {

        Map<String, Integer> productsToBuy = new HashMap<>();
        productsToBuy.put("blaba22", 3432);
        String cartId = "1234567";

        // GIVEN
        CartDtoNoId createdCartNoId = CartTestUtils.createCartDtoNoId("1.0", productsToBuy);
        CartDto updatedCart = CartTestUtils.createCartDto(cartId, "1.1", productsToBuy);

        when(cartService.getCartById(cartId)).thenReturn(updatedCart);
        when(cartService.updateCart(cartId, createdCartNoId)).thenReturn(updatedCart);

        final ObjectMapper objectMapper = new ObjectMapper();
        final String cartToPut = objectMapper.writeValueAsString(updatedCart);
        final ResultActions resultActions = mockMvc.perform(put("/carts/{id}", cartId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(cartToPut));
        // validate the status and response content type
        resultActions.andExpect(status().isOk());
        verify(cartService).updateCart(Mockito.anyString(), cartDtoNoIdArgumentCaptor.capture());
        assertThat(cartDtoNoIdArgumentCaptor.getValue().getUserId().equals("1.1"));
        assertThat(cartDtoNoIdArgumentCaptor.getValue().getProducts()
                .get("blaba22").toString().equals("3432"));
        verifyNoMoreInteractions(cartService);
    }

    @Test
    void testDeleteCart() throws Exception {
        // GIVEN
        String cartId = "1234567";

        // WHEN
        mockMvc.perform(delete("/carts/{id}", cartId))
                // validate the status and response content type
                .andExpect(status().isOk());

        // THEN
        verify(cartService).deleteCart(cartId);
        verifyNoMoreInteractions(cartService);
    }

    private String asJsonString(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
