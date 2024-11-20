package co.simplon.socworkbusiness.services;


import co.simplon.socworkbusiness.dtos.AccountSignIn;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import co.simplon.socworkbusiness.dtos.AccountCreate;
import co.simplon.socworkbusiness.entities.Account;
import co.simplon.socworkbusiness.repositories.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountService {

    public final AccountRepository accounts;

    private final PasswordEncoder encoder;

    public AccountService(AccountRepository accounts, PasswordEncoder encoder) {
        this.accounts = accounts;
        this.encoder = encoder;
    }

    @Transactional
    public void create(AccountCreate inputs) {
        Account entity = new Account();
        entity.setUsername(inputs.username());
        String encoded = encoder.encode(inputs.password());

        accounts.save(entity);
    }

    @Transactional
    public Object signin(AccountSignIn inputs) {
        String username = inputs.username();
        Account entity = accounts.findByUsernameIgnoreCase(username).orElseThrow(() -> new BadCredentialsException(username));
        if (entity != null) {
            return "account found with username" + username;
        }
        return "account not found with username" + username;
    }

}