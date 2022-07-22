package com.example.demo.models;

import lombok.Setter;
import lombok.Getter;

public class Transaction {
    @Setter
    @Getter
    private User fromUser;

    @Getter
    @Setter
    private User toUser;

    @Setter
    @Getter
    private double sum;
}
