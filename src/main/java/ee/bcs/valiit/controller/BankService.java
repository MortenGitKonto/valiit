package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void updateSqlAccountNrBankService(Account account) {
        bankRepository.updateSqlAccountNrBankRepository(account);
    }






    public List<Account> testAllAccountsBankService() {

        List<Account> list = bankRepository.testAllAccountsBankRepository();

        return list;
    }
}