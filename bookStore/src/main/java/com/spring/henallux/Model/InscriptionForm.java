package com.spring.henallux.Model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class InscriptionForm {

	@NotNull
	@Email
	private String email;
	@NotNull
	@Length(min=1, max=255)
	@Pattern(regexp="^[a-zA-Z -]+$")
	private String name;
	@NotNull
	@Length(min=1, max=255)
	@Pattern(regexp="^[a-zA-Z -]+$")
	private String firstName;
	@NotNull
	@Pattern(regexp="^[0-9]{9,255}$")
	private String phoneNumber;
	@NotNull
	@Past
	private Date birthDate;
	@NotNull
	private String street;
	@NotNull
	@Min(value = 1)
	@Max(value = 9999)
	private Integer streetNumber;
	@NotNull
	@Min(value=1000)
	@Max(value=9999)
	private Integer postalCode;
	@NotNull
	@Pattern(regexp="^[a-zA-Z -]+$")
	private String city;
	@NotNull
	@Pattern(regexp="^[a-zA-Z -]+$")
	private String country;
	@NotNull
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!% *? &.])[A-Za-z\\d$@$!%*?&.]{8,}$") //doit contenir une min, un maj, un chiffre, un caractère spécial. Min 8 car !  
	private String password;
	@NotNull
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!% *? &.])[A-Za-z\\d$@$!%*?&.]{8,}$") //doit contenir une min, un maj, un chiffre, un caractère spécial. Min 8 car !  
	private String confirmPassword;
	
	public InscriptionForm(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
