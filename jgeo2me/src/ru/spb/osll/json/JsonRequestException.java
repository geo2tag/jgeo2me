package ru.spb.osll.json;

public class JsonRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 351563119113429364L;
	private int m_errno = Errno.UNKNOWN_ERROR.intValue();
	
	public JsonRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public JsonRequestException(int errno) {
		super(Errno.getErrorByCode(errno)+" " + new Integer(errno).toString());
		setErrno(errno);
		// TODO Auto-generated constructor stub
	}

	public int getErrno() {
		return m_errno;
	}

	public void setErrno(int m_errno) {
		this.m_errno = m_errno;
	}

}
