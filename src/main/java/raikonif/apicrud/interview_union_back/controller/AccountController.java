package raikonif.apicrud.interview_union_back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raikonif.apicrud.interview_union_back.entity.Account;
import raikonif.apicrud.interview_union_back.services.AccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AccountController {

    @GetMapping("/accounts")
    public ResponseEntity<Object> get(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Account> list = accountService.findAll();
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            Account account = accountService.findById(id);
            return new ResponseEntity<Object>(account, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> save(@RequestBody Account account){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Account newAccount = accountService.save(account);
            return new ResponseEntity<Object>(newAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Account account){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Account accountUpdate = accountService.findById(id);
            if(accountUpdate != null){
               accountUpdate.setAccountType(account.getAccountType());
                accountUpdate.setAccountNumber(account.getAccountNumber());
                accountUpdate.setCurrency(account.getCurrency());
                accountUpdate.setAmount(account.getAmount());
                accountUpdate.setClientId(account.getClientId());
                accountUpdate.setBranch(account.getBranch());
                accountUpdate.setCreatedAt(account.getCreatedAt());
                accountService.save(accountUpdate);
                return new ResponseEntity<Object>(accountUpdate, HttpStatus.OK);
            } else {
                map.put("message", "Account not found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Account account = accountService.findById(id);
            if(account != null){
                accountService.delete(account);
                map.put("message", "Account deleted");
                return new ResponseEntity<Object>(account, HttpStatus.OK);
            } else {
                map.put("message", "Account not found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
