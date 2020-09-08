package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    //TEE UUS ACCOUNT
    public void newAccountService(Account account) {

        bankRepository.newAccountRepository(account);

    }

    public void newClientService(Client client) {

        bankRepository.newClientRepository(client);

    }

    //KUTSU KÕIK PANGAKONTOD VÄLJA
    public List<Account> testAllAccountsBankService() {

        List<Account> list = bankRepository.testAllAccountsBankRepository();

        return list;
    }

    //KUTSU ÜHE PANGAKONTO VÄLJA
    public Account testOneAccountBankService(int id) {

        Account result = bankRepository.testOneAccountBankRepository(id);

        return result;
    }

    //KUTSU KÕIK KLIENDID VÄLJA (tabelist clients)
    public List<Client> testAllClientsBankService() {

        List<Client> clientList = bankRepository.testAllClientsBankRepository();

        return clientList;
    }


    //MUUDA ÜHE KONTO KÕIKI ANDMEID
    public void updateSqlAccountNrBankService(Account account) {
        bankRepository.updateSqlAccountNrBankRepository(account);
    }

    public void updateSqlClientNrBankService(Client client) {

        bankRepository.updateSqlClientNrBankRepository(client);
    }


    /////DEPOSIT
    public void sqlDepositAmountService(Account account) {

        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        bankRepository.sqlDepositAmountRepository(account, currentBalance);

        int depositAccountId = bankRepository.getToAccountId(account.getAccountNumber());
        bankRepository.newDepositTransactionRepository(depositAccountId, account.getAmount(), 0, 0);
    }


    /////WITHDRAW
    public void sqlWithdrawAmountService(Account account) {

        Integer currentBalance = bankRepository.selectBalanceRepository(account);
        if (currentBalance >= account.getAmount()) {
            bankRepository.sqlWithdrawAmountRepository(account, currentBalance);
        } else {
            System.out.println("Not enough money in the account to make the transaction");
        }

        int withdrawAccountId = bankRepository.getFromAccountId(account.getAccountNumber());
        bankRepository.newWithdrawTransactionRepository(withdrawAccountId, 0, account.getAmount(), 0);
    }

    /////TRANSFER

    public void sqlTransferAmountService(List<Account> transfer) {

        //Kutsun withdraw välja, töötab tingimusel mis on selle withdraw funktsiooni sees (ehk kui on raha)
        sqlWithdrawAmountService(transfer.get(0));

        //Deklareerin muutuja currentBalance (konto nr1 kohta), ehk kui kontol 1 on vähemalt "amount" siis toimub ülekanne kontole 2
        Integer currentBalanceAcc1 = bankRepository.selectBalanceRepository(transfer.get(0));

        if (currentBalanceAcc1 >= transfer.get(0).getAmount()) {

            sqlDepositAmountService(transfer.get(1));

            //TRANSACTION HISTORY
            int withdrawAccountId = bankRepository.getFromAccountId(transfer.get(0).getAccountNumber());

            int depositAccountId = bankRepository.getToAccountId(transfer.get(1).getAccountNumber());

            bankRepository.newTransferTransactionRepository(withdrawAccountId, depositAccountId, 0, 0, transfer.get(0).getAmount());


        }


    }
}




