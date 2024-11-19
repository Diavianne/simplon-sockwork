package co.simplon.socworkbusiness.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.socworkbusiness.dtos.AccountCreate;
import co.simplon.socworkbusiness.services.AccountService;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountController {
    public final AccountService service;

    public AccountController(AccountService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody AccountCreate inputs) {
	service.create(inputs);
    }
}