package com.hari.model;


import com.hari.domin.VerificationType;
import lombok.Data;


@Data
public class TwoFactorAuth {
    private  boolean isEnabled=false;
    private VerificationType sendTo;

}
