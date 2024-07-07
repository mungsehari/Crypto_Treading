package com.hari.service;

import com.hari.domin.VerificationType;
import com.hari.model.ForgetPasswordToken;
import com.hari.model.User;

public interface ForgetPasswordService {
    ForgetPasswordToken createToken(User user, String id, String otp, VerificationType verificationType,String sendTo);

    ForgetPasswordToken findById(String id);

    ForgetPasswordToken findByUser(Long userId);

    void deleteToken(ForgetPasswordToken token);
}
