/*
 * Copyright 2012 OSLL
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * 3. The name of the author may not be used to endorse or promote
 *    products derived from this software without specific prior written
 *    permission.
 *
 * The advertising clause requiring mention in adverts must never be included.
 */

package ru.spb.osll.json;

//import static ru.spb.osll.json.IRequest.IFilterChannel.*;

import java.io.IOException;


import org.json.me.JSONException;
import org.json.me.JSONObject;

import ru.spb.osll.log.Log;

public class JsonFilterChannelRequest extends JsonBaseRequest {
	private String m_authToken;
	private String m_channel;
	private String m_serverUrl;
	private int m_amount;

	public JsonFilterChannelRequest(String authToken, String channel, int amount,
			String serverUrl) {
		m_authToken = authToken;
		m_channel = channel;
		m_amount = amount;
		m_serverUrl = serverUrl;
	}

	//@Override
	protected JSONObject doRequestInternal() throws JSONException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(IRequest.IFilterChannel.AUTH_TOKEN, m_authToken);
		jsonObject.put(IRequest.IFilterChannel.CHANNEL, m_channel);
		jsonObject.put(IRequest.IFilterChannel.AMOUNT, m_amount);
		Log.out.println(JSON_LOG, jsonObject.toString());

		return JsonBase.instance().doRequest(jsonObject,
				new URI(m_serverUrl + IRequest.IFilterChannel.REQUEST));
	}

}
