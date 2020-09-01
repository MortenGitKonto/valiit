package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankController {

    private final Map<String, Integer> accounts = new HashMap();

    /*public static int bankAccountTest() {
        return bank;
    }*/

    public Map<String, Integer> createAccount(String x, Integer y) {
        accounts.put(x, y);
        return accounts;
    }

    public Integer getBalance(String x) {
        return accounts.get(x);
    }

    public Map<String, Integer> depositMoney(String x, Integer y) {

        accounts.put(x, accounts.get(x) + y);
        return accounts;
    }

    public Map<String, Integer> withdrawMoney(String x, Integer y) {

        accounts.put(x, accounts.get(x) - y);
        return accounts;
    }

    public Map<String, Integer> transferMoney(String x, String y, Integer z) {

        accounts.put(x, accounts.get(x) + z);
        accounts.put(y, accounts.get(y) - z);

        return accounts;
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

    //Lisa raha...
    @PutMapping("/depositIntoAccount")
    public void depositAmount(@RequestBody Account reqaccount) {
        depositMoney(reqaccount.getAccountNumber(), reqaccount.getAmount());
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



