package raikonif.apicrud.interview_union_back.services;

import raikonif.apicrud.interview_union_back.entity.Account;
import raikonif.apicrud.interview_union_back.entity.Client;

import java.util.List;

public interface AccountService {
    public List<Account> findAll();
    public Account save(Account account);
    public Account findById(Long id);
    public void delete(Account account);
}
