package raikonif.apicrud.interview_union_back.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raikonif.apicrud.interview_union_back.dao.AccountDao;
import raikonif.apicrud.interview_union_back.entity.Account;

import java.util.List;

@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return accountDao.save(account);
    }

    @Override
    @Transactional
    public Account findById(Long id) {
        return accountDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Account account) {
        accountDao.delete(account);
    }
}
