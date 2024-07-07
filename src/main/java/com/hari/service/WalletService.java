package com.hari.service;

import com.hari.model.Order;
import com.hari.model.User;
import com.hari.model.Wallet;

public interface WalletService {
    Wallet getUserWallet(User user);
    Wallet addBalance(Wallet wallet ,Long money);
    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender,Wallet receiverWallet ,Long amount) throws Exception;
    Wallet payOrderPayment(Order order, User user) throws Exception;
}
