package com.ascending.demo.api.dao.jdbc;

import java.util.List;

public interface AccountDao {
    Account save(Account account, Employee employee);
    List<Account> getAccounts();
    Account getAccountById(Long id);
}
