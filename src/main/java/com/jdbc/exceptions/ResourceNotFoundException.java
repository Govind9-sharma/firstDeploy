package com.jdbc.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException()
	{
		super("Resourcenot found on server!!");
	}
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
