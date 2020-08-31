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


    /*
        //11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
        //Loo accounte listi
        @PostMapping("/addbankaccount/{a}/{b}")
        public void addAccount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
            createAccount(x, y);
        }
    */
    @PostMapping("/addbankaccount/{a}/{b}")
    public void addAccount(@RequestBody Account account, @PathVariable("a") String x) {
        accounts.put(x, y)
        accounts.(account);
    }
////////////////SIIIIIIIN ÜLEVAL JÄI POOLELI////////////////////////////





    /*
    //22222222222222222222222222222222222222222222222222222222222222222222
    //Vaata kontoseisu
    @GetMapping("/allaccounts/{a}")
    public Integer AmountinAccount(@PathVariable("a") String x) {
        return getBalance(x);
    }
*/


    //Lisa raha...
    @PutMapping("/depositIntoAccount/{a}/{b}")
    public void depositAmount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        depositMoney(x, y);
    }

    //Võta raha...
    @PutMapping("/withdrawFromAccount/{a}/{b}")
    public void withdrawAmount(@PathVariable("a") String x, @PathVariable("b") Integer y) {
        withdrawMoney(x, y);
    }

    //Transfeer
    @PutMapping("/transferMoney/{a}/{b}/{c}")
    public void withdrawAmount(@PathVariable("a") String x, @PathVariable("b") String y, @PathVariable("c") Integer z) {
        transferMoney(x, y, z);
    }
}



