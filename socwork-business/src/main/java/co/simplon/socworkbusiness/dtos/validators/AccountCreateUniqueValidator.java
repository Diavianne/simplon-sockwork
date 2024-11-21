//package co.simplon.socworkbusiness.dtos.validators;
//
//import co.simplon.socworkbusiness.dtos.AccountCreate;
//import co.simplon.socworkbusiness.repositories.AccountRepository;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class AccountCreateUniqueValidator implements ConstraintValidator<AccountCreateUnique, AccountCreate> {
//    private final AccountRepository repos;
//
//    public AccountCreateUniqueValidator(AccountRepository repos) {
//	this.repos = repos;
//    }
//
//    @Override
//    public void initialize(AccountCreateUnique contraintAnnotation) {
//
//    }
//
//    @Override
//    public boolean isValid(AccountCreate account, ConstraintValidatorContext context) {
//	final String username = account.username();
//	if (username == null) {
//	    return true;
//	}
//	return true;
//    }
//}
