package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void newAccountService (Account account) {

        bankRepository.newAccountRepository(account);

    }

    /////DEPOSIT
    @PutMapping("/sqlDepositIntoAccount")
    public void sqlDepositAmountService(Account account) {

        Integer currentBalance = bankRepository.selectBalanceRepository (account);
        bankRepository.sqlDepositAmountRepository(account, currentBalance );

    }



}