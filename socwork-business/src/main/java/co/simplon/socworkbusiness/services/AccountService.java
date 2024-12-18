package co.simplon.socworkbusiness.services;

import co.simplon.socworkbusiness.entities.Role;
import co.simplon.socworkbusiness.repositories.RoleRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.socworkbusiness.config.JwtProvider;
import co.simplon.socworkbusiness.dtos.AccountAuthentificate;
import co.simplon.socworkbusiness.dtos.AccountCreate;
import co.simplon.socworkbusiness.entities.Account;
import co.simplon.socworkbusiness.repositories.AccountRepository;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
	private final RoleRepository roleRepo;

    public AccountService(AccountRepository repo, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, RoleRepository roleRepository) {
	this.repo = repo;
	this.passwordEncoder = passwordEncoder;
	this.jwtProvider = jwtProvider;
	this.roleRepo = roleRepository;
    }

	@Transactional
	public void create(AccountCreate inputs) {

		Account entity = new Account();
		entity.setUsername(inputs.username());
		entity.setPassword(passwordEncoder.encode(inputs.password()));
		Set<Role> roles = roleRepo.findByIsDefaultTrue();
		entity.setRoles(roles);
		repo.save(entity);
	}

    @Transactional
    public Object authentificate(AccountAuthentificate inputs) {
	String username = inputs.username();
	Account account = repo.findAllByUsernameIgnoreCase(username)
		.orElseThrow(() -> new BadCredentialsException(username));

	String row = inputs.password();
	String encoded = account.getPassword();
	if (!passwordEncoder.matches(row, encoded)) {
	    throw new BadCredentialsException(username);
	}
		Set<String> roles = account.getRoles().stream().map(r -> r.getRole().toString()).toList();

	return jwtProvider.create(username, roles);
    }

    public String getAccount() {
	return "Account";
    }
}
