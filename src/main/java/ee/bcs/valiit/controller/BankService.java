package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    //KUTSU KÕIK PANGAKONTOD VÄLJA
    public List<Account> testAllAccountsBankService() {

        List<Account> list = bankRepository.testAllAccountsBankRepository();

        return list;
    }


    //MUUDA ÜHE KONTO KÕIKI ANDMEID
    public void updateSqlAccountNrBankService(Account account) {
        bankRepository.updateSqlAccountNrBankRepository(account);
    }


    //TEE UUS ACCOUNT
    public void newAccountService(Account account) {

        bankRepository.newAccountRepository(account);

    }

    /////DEPOSIT
    public void sqlDepositAmountService(Account account) {

        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        bankRepository.sqlDepositAmountRepository(account, currentBalance);

    }


    /////WITHDRAW
    public void sqlWithdrawAmountService(Account account) {

        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        if (currentBalance >= account.getAmount()) {
            bankRepository.sqlWithdrawAmountRepository(account, currentBalance);
        } else {
            System.out.println("Not enough money in the account to make the transaction");
        }
    }

    /////TRANSFER

    public void sqlTransferAmountService(List<Account> transfer) {

        //Kutsun withdraw välja, töötab tingimusel mis on selle withdraw funktsiooni sees (ehk kui on raha)
        sqlWithdrawAmountService(transfer.get(0));

        //Deklareerin muutuja currentBalance (konto nr1 kohta), ehk kui kontol 1 on vähemalt "amount" siis toimub ülekanne kontole 2
        Integer currentBalanceAcc1 = bankRepository.selectBalanceRepository(transfer.get(0));

        if (currentBalanceAcc1 >= transfer.get(0).getAmount()) {
            sqlDepositAmountService(transfer.get(1));
        }
    }
}