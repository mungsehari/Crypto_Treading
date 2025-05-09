package com.hari.model;

import com.hari.domin.WithdrawalStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private WithdrawalStatus  status;

    private Long amount;

    @ManyToOne
    private User user;

    private LocalDateTime data=LocalDateTime.now();
}
