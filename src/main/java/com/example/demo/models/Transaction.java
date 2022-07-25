package com.example.demo.models;

import lombok.Setter;
import lombok.Getter;

public class Transaction {
    @Setter
    @Getter
    private int fromUserPersonalAccount;

    @Getter
    @Setter
    private int toUserPersonalAccount;

    @Setter
    @Getter
    private double sum;
}
