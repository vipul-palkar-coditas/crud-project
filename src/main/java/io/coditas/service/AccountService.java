package io.coditas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import io.coditas.model.Account;
import io.coditas.model.Branch;
import io.coditas.model.Transaction;
import io.coditas.repository.AccountRepository;
import io.coditas.repository.BranchRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BranchRepository branchRepository;

	public List<Account> getAllAccounts() {
		
		return accountRepository.findAll();
	}
	public List<Transaction> getTransactionsByAccountNo(Long accountNo)
	{
		List<Transaction> t=accountRepository.getTransactionsByAccountNo(accountNo);
		List<Transaction> t2=new ArrayList<>();
		for(int i=0;i<t.size();i++)
		{
			t2.add((Transaction) Hibernate.unproxy(t.get(i)));
		}
		return t2;
	}

	public Account addAccount(Account account, Integer branchIfsc) 
	{
		Optional<Branch> branch=branchRepository.findById(branchIfsc);
		if(branch.isEmpty())
		account.setBranch(branch.get());
		return accountRepository.save(account);
	}

	public Account modifyAccount(Account account,Long accountNo) throws Exception
	{
		
		Optional<Account> fetchedAccount=Optional.ofNullable(accountRepository.getAccountByAccountNo(accountNo));
		if(fetchedAccount.isEmpty())
		{
			throw new Exception("Account Not found");
		}
		else
		{
			account.setAccountNo(accountNo);
		}
	    accountRepository.updateAccountTypeById(account.getAccountType(), accountNo);
	    return account;
	    }

	public void deleteByAccountNo(Long accountNo) {
		accountRepository.deleteById(accountNo);
	}

	
}
