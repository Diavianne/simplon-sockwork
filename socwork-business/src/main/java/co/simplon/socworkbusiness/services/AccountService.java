package co.simplon.socworkbusiness.services;

import org.springframework.stereotype.Service;

import co.simplon.socworkbusiness.dtos.AccountCreate;
import co.simplon.socworkbusiness.entities.Account;
import co.simplon.socworkbusiness.repositories.AccountRepository;

@Service
public class AccountService {

    public final AccountRepository repos;

    public AccountService(AccountRepository repos) {
	this.repos = repos;
    }

    public void create(AccountCreate inputs) {
	Account entity = new Account();
	entity.setUsername(inputs.username());
	entity.setPassword(inputs.password());
	repos.save(entity);
    }

}
