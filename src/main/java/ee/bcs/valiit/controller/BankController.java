package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankController {

    private final Map<String, Integer> accounts = new HashMap();
    @Autowired
    private NamedParameterJdbcTemplate template;

    @GetMapping("sqltestBalance")
    public String testSql() {
        String sql = "select balance from bank_accounts where id = :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", 2);
        String vastus = template.queryForObject(sql, paramMap, String.class);
        return vastus;
    }

    /*@PutMapping("sqlUpdateAccountInfo")
    public void updateSqlAccountNr() {
        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNr, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", 222);
        paramMap.put("accountNr", "ACCOUNT2");
        paramMap.put("balance", 220000);
        paramMap.put("id", 2);
        template.update(sql, paramMap);
    }*/

    @PutMapping("sqlUpdateAccountInfo")
    public void updateSqlAccountNr(@RequestBody Account account) {
        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNr, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNr", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        paramMap.put("id", account.getId());
        template.update(sql, paramMap);
    }


    /*@PostMapping("sqlNewRow")
    public void newRow() {
        String sql = "INSERT INTO bank_accounts (id, client_id, account_nr, balance) VALUES (:id, :clientId, :accountNr, :balance)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", 2);
        paramMap.put("clientId", 202);
        paramMap.put("accountNr", "ACCOUNT2");
        paramMap.put("balance", 20000);
        template.update(sql, paramMap);
    }*/

    @PostMapping("sqlNewAccount")
    public void newAccount(@RequestBody Account account) {
        String sql = "INSERT INTO bank_accounts (id, client_id, account_nr, balance) VALUES (:id, :clientId, :accountNr, :balance)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id", account.getId());
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNr", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        template.update(sql, paramMap);
    }

    //accounts.put(reqaccount.getAccountNumber(), reqaccount.getAmount());

        /*String sql = "update bank_accounts set column1 = :column1, column2=:column2;
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", "ACCOUNT1");
        paramMap.put("account_nr", "ACC3");
        paramMap.put("account_nr", "ACC3");
        paramMap.put("id", 1);
        template.update(sql, paramMap);
           }*/

    /*@PutMapping("sqlUpdateBalance")
    public void updateSqlBalance() {
        String sql = "update bank_accounts set balance = :balance where id = :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("balance", 4949);
        paramMap.put("id", 1);
        template.update(sql, paramMap);
    }*/


    public Integer getBalance(String x) {
        return accounts.get(x);
    }

    public void depositMoney(String x, Integer y) {
        accounts.put(x, accounts.get(x) + y);
    }

    public void withdrawMoney(String x, Integer y) {
        accounts.put(x, accounts.get(x) - y);
    }

    public void transferMoney(String x, String y, Integer z) {
        accounts.put(x, accounts.get(x) + z);
        accounts.put(y, accounts.get(y) - z);
    }

    //11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    //Loo accounte listi
    /*@PostMapping("/addbankaccount/{a}/{b}")
    public void addAccount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        createAccount(x, y);
    }*/

    @PostMapping("/addbankaccount")
    public void addAccount(@RequestBody Account reqaccount) {
        accounts.put(reqaccount.getAccountNumber(), reqaccount.getAmount());
    }


    //22222222222222222222222222222222222222222222222222222222222222222222
    //Vaata kontoseisu
    @GetMapping("/allaccounts/{a}")
    public Integer AmountinAccount(@PathVariable("a") String x) {
        return getBalance(x);
    }


//33333333333333333333333333333333333333333333333333333333333333333333333
    /*//Lisa raha...
    @PutMapping("/depositIntoAccount/{a}/{b}")
    public void depositAmount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        depositMoney(x, y);
    }*/

    /*//Lisa raha...
    @PutMapping("/depositIntoAccount")
    public void depositAmount(@RequestBody Account reqaccount) {
        depositMoney(reqaccount.getAccountNumber(), reqaccount.getAmount());
    }*/

    @PutMapping("/sqlDepositIntoAccount")
    public void sqlDepositAmount(@RequestBody Account reqaccount) {

        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNr, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", reqaccount.getClientId());
        paramMap.put("accountNr", reqaccount.getAccountNumber());
        paramMap.put("balance", reqaccount.getAmount());
        paramMap.put("id", reqaccount.getId());
        template.update(sql, paramMap);

        depositMoney(reqaccount.getAccountNumber(), reqaccount.getAmount());
    }

    @PutMapping("sqlUpdateAccountInfo")
    public void updateSqlAccountNr(@RequestBody Account account) {
        String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNr, balance= :balance where id= :id";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("clientId", account.getClientId());
        paramMap.put("accountNr", account.getAccountNumber());
        paramMap.put("balance", account.getAmount());
        paramMap.put("id", account.getId());
        template.update(sql, paramMap);
    }

    //4444444444444444444444444444444444444444444444444444444444444444444444
    //Võta raha...
    /*@PutMapping("/withdrawFromAccount/{a}/{b}")
    public void withdrawAmount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        withdrawMoney(x, y);
    }*/

    @PutMapping("/withdrawFromAccount")
    public void withdrawAmount(@RequestBody Account reqaccount) {
        withdrawMoney(reqaccount.getAccountNumber(), reqaccount.getAmount());
    }

    //5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555

    //Transfeer
    /*@PutMapping("/transferMoney/{a}/{b}/{c}")
    public void transferMoney(@PathVariable("a") String x, @PathVariable("b") String y, @PathVariable("c") Integer z) {
        transferMoney(x, y, z);
    }*/

    @PutMapping("/transferMoney")
    public void transferMoney(@RequestBody TransferMoneyRequest transferRequest) {
        transferMoney(transferRequest.getAccountNumber(), transferRequest.getAccountNumber2(), transferRequest.getAmount());


    }

}

