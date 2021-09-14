package io.coditas.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_DEFAULT)
public class Account implements Serializable{
	
	@Id
	@Column(name="account_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNo;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="account_balence")
	private Double accountBalence;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","account"}) 
	@JsonIgnoreProperties("account")
	@OneToMany(orphanRemoval = false, fetch = FetchType.EAGER,mappedBy = "account",cascade = CascadeType.ALL)
	private List<Transaction> transactions;


	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","accounts"}) 
	//@JsonIgnoreProperties("accounts")
	@ManyToOne(fetch = FetchType.LAZY)
	private Branch branch;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","account"}) 
	//@JsonIgnoreProperties("account")
	@OneToOne(orphanRemoval = false, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Address address;
	
	public Account() {}

	public Account(Long accountNo,String accountType,Double accountBalence, String customerName) {
		super();
		this.accountType = accountType;
		this.customerName = customerName;
		this.accountBalence = accountBalence;
		this.accountNo=accountNo;
	}

	public <T> List<Transaction> initializeAndUnproxy(List<Transaction> transaction) {
    if (transaction == null) {
        throw new 
           NullPointerException("Entity passed for initialization is null");
    }

    Hibernate.initialize(transaction);
    if (transaction instanceof HibernateProxy) {
    	transaction = (List<Transaction>) ((HibernateProxy) transaction).getHibernateLazyInitializer()
                .getImplementation();
    }
    return transaction;
}

	@JsonIgnore
	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Double getAccountBalence() {
		return accountBalence;
	}

	public void setAccountBalence(Double accountBalence) {
		this.accountBalence = accountBalence;
	}
	
	public  List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions( List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountBalence, accountNo, accountType, address, branch, customerName, transactions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountBalence, other.accountBalence) && Objects.equals(accountNo, other.accountNo)
				&& Objects.equals(accountType, other.accountType) && Objects.equals(address, other.address)
				&& Objects.equals(branch, other.branch) && Objects.equals(customerName, other.customerName)
				&& Objects.equals(transactions, other.transactions);
	}
	
}
