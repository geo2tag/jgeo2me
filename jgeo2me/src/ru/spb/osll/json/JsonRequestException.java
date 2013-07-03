package ru.spb.osll.json;

public class JsonRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 351563119113429364L;
	
	public static final String EMPTY_RESPONSE_MESSAGE = "Server response is empty";
	public static final String ERRNO_NOT_EXPECTED_MESSAGE = "Server response is empty";
	public static final String IO_EXCEPTION = "IO exception";
	public static final String JSON_RESPONSE_EXCEPTION = "Response format is invalid";
	
	public JsonRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public JsonRequestException(int errno) {
		super(ERRNO_NOT_EXPECTED_MESSAGE + new Integer(errno).toString());
		// TODO Auto-generated constructor stub
	}

}
