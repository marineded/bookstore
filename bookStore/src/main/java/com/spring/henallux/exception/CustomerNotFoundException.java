package com.spring.henallux.exception;

public class CustomerNotFoundException extends Exception{

private String message;
	
	public CustomerNotFoundException (String message)
	{
		this.message = message;
	}
	
	public String getMessage ()
	{
		return message;
	}
	
}
