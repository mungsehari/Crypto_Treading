package com.hari.service;

import com.hari.model.PaymentDetails;
import com.hari.model.User;

public interface PaymentDetailsService {

    public PaymentDetails addPaymentDetails(
            String accountNumber,
            String accountHolderName,
            String ifsc,
            String bankName,
            User user
    );

    public  PaymentDetails getUsersPaymentDetails(User user);
}
