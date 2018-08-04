package com.careerday.backenddeveloper.accounts.support;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    public Account findByUsername(String userName);
}
