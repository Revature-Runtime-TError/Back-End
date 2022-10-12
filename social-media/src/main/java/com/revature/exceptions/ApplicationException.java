package com.revature.exceptions;

public class ApplicationException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Internal Database Server Error! Please try again later!";
	}

}
