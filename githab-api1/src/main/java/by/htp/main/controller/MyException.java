package by.htp.main.controller;

public class MyException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	public MyException() {
		super();
	}
	
	public MyException(String message) {
		super(message);
	}
	
	public MyException(Exception e) {
		super(e);
	}
	
	public MyException(String message, Exception e) {
		super(message, e);
	}

}
