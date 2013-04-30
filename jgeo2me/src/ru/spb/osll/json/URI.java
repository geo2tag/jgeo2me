/**
 * 
 */
package ru.spb.osll.json;

/**
 * Wrapper around string.
 *
 */
public class URI {
	private String m_uri;
	
	URI(String uri){
		m_uri = uri;
	}
	
	public String toString(){
		return m_uri;
	}
	
}
