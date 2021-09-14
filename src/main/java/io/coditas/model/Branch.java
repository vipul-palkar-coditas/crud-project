package io.coditas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="branches")
public class Branch implements Serializable {
	@Id
	@Column(name="branch_ifsc")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer branchIfsc;
	
	@Column(name="branch_area")
	private String branchArea;
	
	@Column(name="branch_city")
	private String branchCity;
	
	@Column(name="branch_state")
	private String branchState;
	
	@Column(name="branch_country")
	private String branch_country;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","branch"}) 
	//@JsonIgnoreProperties("branch")
	@OneToMany(fetch = FetchType.LAZY,targetEntity = Account.class,mappedBy = "branch")
	private List<Account> accounts;

	public Branch() {
		super();
	}

	public Branch(Integer branchIfsc, String branchArea, String branchCity, String branchState, String branch_country,
			List<Account> accounts) {
		super();
		this.branchIfsc = branchIfsc;
		this.branchArea = branchArea;
		this.branchCity = branchCity;
		this.branchState = branchState;
		this.branch_country = branch_country;
		this.accounts = accounts;
	}

	public Integer getBranchIfsc() {
		return branchIfsc;
	}

	public void setBranchIfsc(Integer branchIfsc) {
		this.branchIfsc = branchIfsc;
	}

	public String getBranchArea() {
		return branchArea;
	}

	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchState() {
		return branchState;
	}

	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}

	public String getBranch_country() {
		return branch_country;
	}

	public void setBranch_country(String branch_country) {
		this.branch_country = branch_country;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
