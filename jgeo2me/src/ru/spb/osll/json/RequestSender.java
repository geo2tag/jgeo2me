/**
 * 
 */
package ru.spb.osll.json;


import java.io.IOException;

import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 * Class performs common routines for REST requests sending
 * @author Mark Zaslavskiy
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
				throw new JsonRequestException(Errno.JSON_RESPONSE_EXCEPTION.intValue());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new JsonRequestException(Errno.IO_EXCEPTION.intValue());
			}
			if (jsonObject != null){
				System.out.println("jsonObject != null");
				System.out.println(jsonObject.toString());
				break;
			}
	
		}
		
		if (jsonObject == null) {
			System.out.println("jsonObject == null");
			throw new JsonRequestException(Errno.EMPTY_RESPONSE_EXCEPTION.intValue());
		}
		
		res.parseJson(jsonObject);
		int errno = res.getErrno();
		
		for (int i=0 ; i < errnos.length; i++){
			if (errno == errnos[i]) return;
		}
		
		throw new JsonRequestException(errno);
		
	}
}
