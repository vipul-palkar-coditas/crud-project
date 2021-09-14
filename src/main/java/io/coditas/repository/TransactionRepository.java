package io.coditas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.coditas.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	@Query("select new io.coditas.model.Transaction(t.transactionId,t.transactionType) from Transaction t where t.transactionId=:transactionId")
	Transaction getTransactionOnly(@Param("transactionId") Long transactionId);	
}
