package com.hari.repository;

import com.hari.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode ,Long> {
    public VerificationCode findByUserId(Long  userId);
}
