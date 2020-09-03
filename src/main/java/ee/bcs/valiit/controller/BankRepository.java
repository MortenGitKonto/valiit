package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;


    public void updateSqlAccountNrBankRepository(Account account) {

            String sql = "update bank_accounts set client_id = :clientId, account_nr= :accountNr, balance= :balance where id= :id";
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("clientId", account.getClientId());
            paramMap.put("accountNr", account.getAccountNumber());
            paramMap.put("balance", account.getAmount());
            paramMap.put("id", account.getId());
            template.update(sql, paramMap);
    }

    public List<Account> testAllAccountsBankRepository(){

        String sql = "select * from bank_accounts order by id";
        Map<String, Object> paramMap = new HashMap();
        List<Account> resultList = template.query(sql, paramMap, new ObjectRowMapper());
        return resultList;
    }
}
