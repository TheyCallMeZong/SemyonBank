package com.example.demo.repository;

import com.example.demo.models.Replenishment;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface Operation {
    boolean transfer(Transaction transaction);
    void lickAmount(Replenishment replenishment);
}
