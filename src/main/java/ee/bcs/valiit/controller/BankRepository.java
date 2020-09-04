package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    //KUTSU KÕIK PANGAKONTOD VÄLJA
    public List<Account> testAllAccountsBankRepository() {

        String sql = "select * from bank_accounts order by id";
        Map<String, Object> paramMap = new HashMap();
        List<Account> resultList = template.query(sql, paramMap, new ObjectRowMapper());
        return resultList;
    }

    //MUUDA ÜHE KONTO KÕIKI ANDMEID
    public void updateSqlAccountNrBankRepository(Account account) {

        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNumber, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        paramMap.put("id", account.getId());
        template.update(sql, paramMap);
    }


    //TEE UUS ACCOUNT
    public void newAccountRepository(Account account) {
        String sql = "INSERT INTO bank_accounts (id, client_id, account_nr, balance) VALUES (:id, :clientId, :accountNumber, :balance)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", account.getId());
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNumber", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        template.update(sql, paramMap);
    }

    /////DEPOSIT JA WITHDRAW (MÕLEMAD VAJAVAD KAHT ETAPPI)

    public Integer selectBalanceRepository(Account account) {

        String sqlGet = "select balance from bank_accounts where id = :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", account.getId());
        Integer balanceNow = template.queryForObject(sqlGet, paramMap, Integer.class);
        return balanceNow;
    }

    public void sqlDepositAmountRepository (Account account, Integer balanceNow) {
        String sqlDeposit = "update bank_accounts set balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", account.getId());
        paramMap.put("balance", (balanceNow + account.getAmount()));
        template.update(sqlDeposit, paramMap);
    }


    public void sqlWithdrawAmountRepository (Account account, Integer balanceNow) {

        String sqlWithdraw = "update bank_accounts set balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", account.getId());
        paramMap.put("balance", (balanceNow - account.getAmount()));
        template.update(sqlWithdraw, paramMap);
    }

}
