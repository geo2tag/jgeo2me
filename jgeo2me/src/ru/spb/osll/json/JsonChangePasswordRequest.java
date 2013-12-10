package ru.spb.osll.json;

import java.io.IOException;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import ru.spb.osll.log.Log;

public class JsonChangePasswordRequest extends JsonBaseRequest {
	private String m_login;
	private String m_oldPassword;
	private String m_newPassword;
	
	public JsonChangePasswordRequest(String login, String oldPassword, String newPassword, String serverUrl){
		super(serverUrl);
		m_login = login;
		m_newPassword = newPassword;
		m_oldPassword = oldPassword;
	}

	//@Override
	protected JSONObject doRequestInternal() throws JSONException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(IRequest.IChangePassword.LOGIN, m_login);
		jsonObject.put(IRequest.IChangePassword.NEW_PASSWORD, m_newPassword);
		jsonObject.put(IRequest.IChangePassword.PASSWORD, m_oldPassword);
		Log.out.println(JSON_LOG, jsonObject.toString());
		return JsonBase.instance().doRequest(jsonObject, new URI(getServerUrl() + IRequest.ILogin.REQUEST));
	}
}
