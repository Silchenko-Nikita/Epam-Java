package com.epam.rd.autotasks.wallet;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class WalletImpl implements Wallet {
    private List<Account> accounts;
    private PaymentLog log;
    private final ReentrantLock rLock = new ReentrantLock();

    public WalletImpl(List<Account> accounts, PaymentLog log) {
        this.accounts = accounts;
        this.log = log;
    }

    @Override
    public void pay(String recipient, long amount) throws Exception {
        rLock.lock();
        Account selectedAccount = null;
        try {
            selectedAccount = accounts.stream()
                    .filter(account -> account.balance() >= amount)
                    .findFirst()
                    .orElseThrow(() -> new ShortageOfMoneyException(recipient, amount));


            selectedAccount.pay(amount);
            log.add(selectedAccount, recipient, amount);
        } finally {
            rLock.unlock();
        }
    }
}
