package com.example.demo.service;

import com.example.demo.UsersRepository;
import com.example.demo.models.Replenishment;
import com.example.demo.models.Transaction;
import com.example.demo.repository.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationService implements Operation {

    @Override
    public boolean transfer(Transaction transaction) {
        var toUser = UsersRepository.USER_LIST.stream().filter(x -> x.getPersonalAccount() == transaction.getToUserPersonalAccount())
                .findAny().orElse(null);

        if (toUser == null) {
            return false;
        }
        var fromUser = UsersRepository.USER_LIST.stream().filter(x -> x.getPersonalAccount() == transaction.getFromUserPersonalAccount())
                .findAny().orElse(null);

        if (fromUser != null && fromUser.getBalance() > transaction.getSum()) {
            fromUser.setBalance(fromUser.getBalance() - transaction.getSum());
            toUser.setBalance(toUser.getBalance() + transaction.getSum());
            return true;
        }
        return false;
    }

    @Override
    public void lickAmount(Replenishment replenishment) {
        if (replenishment.getSum() > 0){
            System.err.println(replenishment.getSum());
            System.err.println(replenishment.getUser());
            replenishment.getUser().setBalance(replenishment.getUser().getBalance() + replenishment.getSum());
        }
    }
}
