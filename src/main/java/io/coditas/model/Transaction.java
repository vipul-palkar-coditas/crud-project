package io.coditas.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable {

	
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@Column(name="transaction_amount")
	private Double amount;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="date_time")
	private LocalDateTime dateTime;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","transactions"}) 
	//@JsonIgnoreProperties("transactions")
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Account account;

		public Transaction() {
		super();
	}

	public Transaction(Long transactionId, Double amount, String transactionType, LocalDateTime dateTime,
			Account account) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.dateTime = dateTime; 
		this.account =account;
		}
	
	public Transaction(Long transactionId ,String transactionType) {
		super();
		this.transactionId=transactionId;
		this.transactionType = transactionType;
		
	}
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, amount, dateTime, transactionId, transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(account, other.account) && Objects.equals(amount, other.amount)
				&& Objects.equals(dateTime, other.dateTime) && Objects.equals(transactionId, other.transactionId)
				&& Objects.equals(transactionType, other.transactionType);
	}
	


	public <T> Account initializeAndUnproxy(Account account) {
	    if (account == null) {
	        throw new 
	           NullPointerException("Entity passed for initialization is null");
	    }

	    Hibernate.initialize(account);
	    if (account instanceof HibernateProxy) {
	    	account = (Account) ((HibernateProxy) account).getHibernateLazyInitializer()
	                .getImplementation();
	    }
	    return account;
	}
}
