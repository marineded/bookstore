package com.spring.henallux.exception;

public class CustomerAlreadyExistException extends Exception{

private String message;
	
	public CustomerAlreadyExistException (String message)
	{
		this.message = message;
	}
	
	public String getMessage ()
	{
		return message;
	}

}
