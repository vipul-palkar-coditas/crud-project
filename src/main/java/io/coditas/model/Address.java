package io.coditas.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="address")
public class Address implements Serializable{

	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	
	@Column(name="landmark")
	private String landmark;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="pincode")
	private String pincode;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","address"}) 
	//@JsonIgnoreProperties("address")
	@OneToOne(fetch =  FetchType.LAZY, mappedBy = "address",cascade = CascadeType.ALL)
	private Account account;

	public Address(Integer addressId, String landmark, String city, String state, String country, String pincode,
			Account account) {
		super();
		this.addressId = addressId;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.account = account;
	}
	public Address() {
		super();
	}
	
	

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
