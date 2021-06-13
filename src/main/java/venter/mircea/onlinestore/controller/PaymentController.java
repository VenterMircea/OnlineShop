package venter.mircea.onlinestore.controller;

import venter.mircea.onlinestore.model.dto.PaymentDataDto;
import venter.mircea.onlinestore.model.dto.PaymentLinkDto;
import venter.mircea.onlinestore.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<PaymentLinkDto> createPayment(@Valid @RequestBody PaymentDataDto paymentDataDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.createPayment(paymentDataDto));
    }
}
