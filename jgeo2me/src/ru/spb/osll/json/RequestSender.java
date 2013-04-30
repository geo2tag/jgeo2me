/**
 * 
 */
package ru.spb.osll.json;

import org.json.me.JSONObject;

/**
 * Class performs common routines for REST requests sending
 * @author Mark Zsalavskiy
 *
 */
public class RequestSender {
	private static final int REQUEST_ATTEMPTS = 3;

	public static void performRequest(JsonBaseRequest req, JsonBaseResponse res, int[] errnos)
	throws JsonRequestException
	{
		JSONObject jsonObject = null;
		
		for (int i=0; i < REQUEST_ATTEMPTS; i++){
			jsonObject = req.doRequest();
			if (jsonObject != null) break;
	
		}
		
		if (jsonObject == null) {
			throw new JsonRequestException("Server response is empty!!");
		}
		
		res.parseJson(jsonObject);
		int errno = res.getErrno();
		
		for (int i=0 ; i < errnos.length; i++){
			if (errno == errnos[i]) return;
		}
		
		throw new JsonRequestException("Errno value is not expected: "+ new Integer(errno).toString());
		
	}
}
