package io.coditas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.coditas.model.Account;
import io.coditas.model.Transaction;
import io.coditas.service.AccountService;
import io.coditas.service.BranchService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accounts")
	public List<Account> getAllAccount()
	{
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/transaction/{accountNo}")
	public List<Transaction> getTransactionsByAccountNo(@PathVariable("accountNo") Long accountNo)
	{
		return accountService.getTransactionsByAccountNo(accountNo);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Account> addAccount( @RequestBody Account account, @RequestParam("branchIfsc") Integer branchIfsc)
	{ 	
		return new ResponseEntity<Account>(accountService.addAccount(account, branchIfsc),HttpStatus.ACCEPTED.CREATED);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<Account> modifyAccount(@RequestBody Account account, @RequestParam("accountNo") Long accountNo)
	throws Exception
	{
		Account fetchedAccount=accountService.modifyAccount(account, accountNo);
		return ResponseEntity.ok(fetchedAccount);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Account> deleteAccount(@RequestParam("accountNo") Long accountNo)
	{
		accountService.deleteByAccountNo(accountNo);
		return ResponseEntity.ok().build();
	}
}
