package io.coditas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.coditas.model.Account;
import io.coditas.model.Transaction;
public interface AccountRepository extends JpaRepository<Account, Long>{

	@Modifying
	@Transactional
	@Query("update Account a set a.accountType=:accountType where a.accountNo=:accountNo")
	void updateAccountTypeById(@Param("accountType") String accountType, @Param("accountNo") Long accountNo);

	@Query("select a from Account a where a.accountNo=:accountNo")
	Account getAccountByAccountNo(@Param("accountNo") Long accountNo);
	
	//@Query("select a.transactions from Account a join a.transactions t where a.accountNo=:accountNo")
	//@Query("select a.transactions from Account a")//Fetch All the transactions
	@Query("select t from Account a join a.transactions t")
	List<Transaction> getTransactionsByAccountNo(@Param("accountNo") Long accountNo);
}
