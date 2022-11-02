package com.example.Flexserver.exception;

public class CommonDataAccessException	extends CommonRuntimeException {

	private static final long serialVersionUID = 5218388054594281096L;


	public CommonDataAccessException(Throwable cause, String exception) {
		super(cause, exception);
	}

	public CommonDataAccessException(String exception, Object data){
		super(exception, data);
	}
	
  
	public CommonDataAccessException(String exception) {
		super(exception);
	}
  
  
	public CommonDataAccessException(Throwable cause, String exception, Object[] placeholderValues) {
		super(cause, exception, placeholderValues);
	}
	
  
	public CommonDataAccessException(String exception, Object[] placeholderValues) {
		super(exception, placeholderValues);
	}

}
