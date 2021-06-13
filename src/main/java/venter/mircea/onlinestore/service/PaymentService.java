package venter.mircea.onlinestore.service;

import venter.mircea.onlinestore.model.dto.PaymentDataDto;
import venter.mircea.onlinestore.model.dto.PaymentLinkDto;

public interface PaymentService {

    PaymentLinkDto createPayment(PaymentDataDto paymentDataDto);
}
