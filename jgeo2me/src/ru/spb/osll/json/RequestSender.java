/**
 * 
 */
package ru.spb.osll.json;


import java.io.IOException;

import org.json.me.JSONException;
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
			try {
				jsonObject = req.doRequest();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new JsonRequestException(JsonRequestException.JSON_RESPONSE_EXCEPTION);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new JsonRequestException(JsonRequestException.IO_EXCEPTION);
			}
			if (jsonObject != null) break;
	
		}
		
		if (jsonObject == null) {
			throw new JsonRequestException(JsonRequestException.EMPTY_RESPONSE_MESSAGE);
		}
		
		res.parseJson(jsonObject);
		int errno = res.getErrno();
		
		for (int i=0 ; i < errnos.length; i++){
			if (errno == errnos[i]) return;
		}
		
		throw new JsonRequestException(errno);
		
	}
}
