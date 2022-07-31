package com.example.demo.service;

import com.example.demo.models.Replenishment;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class OperationService{

    private final Operation operation;

    @Autowired
    public OperationService(Operation operation) {
        this.operation = operation;
    }

    public boolean transfer(Transaction transaction) {
        var toUser = operation.findByPersonalAccount(transaction.getToUserPersonalAccount());

        if (toUser == null) {
            return false;
        }
        var fromUser = operation.findByPersonalAccount(transaction.getFromUserPersonalAccount());

        if (fromUser != null && fromUser.getBalance() > transaction.getSum()) {
            fromUser.setBalance(fromUser.getBalance() - transaction.getSum());
            toUser.setBalance(toUser.getBalance() + transaction.getSum());
            operation.saveAll(Arrays.asList(toUser, fromUser));
            return true;
        }
        return false;
    }

    public void lickAmount(Replenishment replenishment) {
        User user = replenishment.getUser();
        if (replenishment.getSum() > 0){
            user.setBalance(user.getBalance() + replenishment.getSum());
            operation.save(user);
        }
    }
}
