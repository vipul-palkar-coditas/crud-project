package io.coditas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.coditas.model.Account;
import io.coditas.model.Transaction;
import io.coditas.repository.AccountRepository;
import io.coditas.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public Transaction getTransaction(Long transationId) {
		
		Transaction fetchedtransactionById=transactionRepository.getTransactionOnly(transationId);
		return fetchedtransactionById;
	}
	
	public Transaction addTransaction(Transaction transaction,Long accountNo)
	{
		Optional<Transaction> enteredTransaction=Optional.ofNullable(transaction);
		if(enteredTransaction.isPresent())
		{
		Optional<Account> account=accountRepository.findById(accountNo);
		if(account.isPresent())
			{
			if(transaction.getTransactionType().equalsIgnoreCase("d"))
			{
				account.get().setAccountBalence(account.get().getAccountBalence()+transaction.getAmount());
				transaction.setDateTime(LocalDateTime.now());
				transaction.setAccount(account.get());
				transactionRepository.save(transaction);
				return transaction;
			}
			if(transaction.getTransactionType().equalsIgnoreCase("w"))
			{
				if(account.get().getAccountBalence()>transaction.getAmount())
				{
					account.get().setAccountBalence(account.get().getAccountBalence()-transaction.getAmount());
					transaction.setDateTime(LocalDateTime.now());
					transaction.setAccount(account.get());
					transactionRepository.save(transaction);
					return transaction;
				}
			}
		}
		
	}
	return new Transaction();
	}

	public void delteTransaction(Long transactionNo) {
		transactionRepository.deleteById(transactionNo);
		
	}

}
