package ru.spb.osll.json;

import java.io.IOException;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import ru.spb.osll.log.Log;

public class JsonFilterSubstringRequest extends JsonBaseRequest {
	private String m_authToken;
	private int m_tagNumber;
	private String m_field;
	private String m_substring;	

	
	public JsonFilterSubstringRequest(String authToken,  String field, String substring, String serverUrl, int tagNumber){
		this(authToken, field, substring, serverUrl);
		m_tagNumber = tagNumber;
	}
	
	public JsonFilterSubstringRequest(String authToken,  String field, String substring, String serverUrl){
		m_authToken = authToken;
		m_field = field;
		m_substring = substring;
		setServerUrl(serverUrl);
	}
	
	protected JSONObject doRequestInternal() throws JSONException, IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(IRequest.IFilterSubstring.AUTH_TOKEN, m_authToken);
		jsonObject.put(IRequest.IFilterSubstring.FIELD, m_field);
		jsonObject.put(IRequest.IFilterSubstring.SUBSTRING, m_substring);
		
		if (m_tagNumber > 0)
			jsonObject.put(IRequest.IFilterSubstring.TAG_NUMBER, m_tagNumber);
		
		Log.out.println(JSON_LOG, jsonObject.toString());
		
		return JsonBase.instance().doRequest(jsonObject, new URI(getServerUrl() + IRequest.IFilterSubstring.REQUEST));
	}	
}
