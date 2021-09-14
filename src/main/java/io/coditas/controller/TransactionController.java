package io.coditas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.coditas.model.Transaction;
import io.coditas.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/id")
	public ResponseEntity<Transaction> getTransacton(@RequestParam("transactionId") Long transationId)
	{
		return new ResponseEntity<Transaction>(transactionService.getTransaction(transationId),HttpStatus.FOUND) ;
	}
	
	
	@PostMapping("/transactions")
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction,@RequestParam("accountNo") Long accountNo)
	{
		transactionService.addTransaction(transaction, accountNo);
		return new ResponseEntity<Transaction>(transaction,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Transaction> deleteTransaction(@RequestParam("transactionNo") Long transactionNo)
	{
		transactionService.delteTransaction(transactionNo);
		return ResponseEntity.ok().build();
	}
}
