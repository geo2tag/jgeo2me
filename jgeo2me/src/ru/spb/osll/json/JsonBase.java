/*
 * Copyright 2010-2011  Vasily Romanikhin  bac1ca89@gmail.com
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

/*! ---------------------------------------------------------------
 * PROJ: OSLL/geo2tag
 * ---------------------------------------------------------------- */

package ru.spb.osll.json;

//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

//import java.net.URI;

/*import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;*/
import org.json.me.JSONException;
import org.json.me.JSONObject;

import ru.spb.osll.log.Log;

public class JsonBase {
	public static String LOG = "JsonLog";
	
  //  private final int TIMEOUT_MILLISEC = 20000; // = 20 seconds
    
    private static JsonBase instanceJSBase;
    public static synchronized JsonBase instance(){
    	if (instanceJSBase == null)
    		instanceJSBase = new JsonBase();
    	return instanceJSBase;
    }
    
    
	public  JSONObject doRequest(JSONObject jsonObject, URI  uri) 
			throws IOException, JSONException, IllegalArgumentException{
		byte[] postData = jsonObject.toString().getBytes("UTF-8");
		HttpConnection c = null;
	    OutputStream os = null;
	    InputStreamReader is = null;
	    int rc;
	    JSONObject result = null;
	    String resultString = "";
	    Log.out.println("JsonLog", uri.toString());
	        
	    try {
	        c = (HttpConnection)Connector.open(uri.toString());

	        c.setRequestMethod(HttpConnection.POST);
	        c.setRequestProperty("User-Agent",
	            "Profile/MIDP-2.0 Configuration/CLDC-1.0");
	        Log.out.println("JsonLog", "Opening outputStream");
	        os = c.openOutputStream();
	        
	        os.write(postData);
	        rc = c.getResponseCode();
	        
	        if (rc != HttpConnection.HTTP_OK) {
	            throw new IOException("HTTP response code: " + rc);
	        }

	        // Got response
	        is = new InputStreamReader( c.openInputStream(), "UTF-8");
	        
	        int ch;
	        while ((ch = is.read()) != -1)
	            resultString += (char)ch;
	       
	        Log.out.println("HTTP_POST_REQUEST",resultString);
	        
	        result = new JSONObject(resultString);
	         
	    } catch (Exception e) {
	        e.printStackTrace();
	    	Log.out.println("JsonLog", "EXCEPTION!!!\n" + e.toString());
	       
	    }finally{
	    	if (os != null)
	            os.close();
	    	if (c != null)
	            c.close();
	    	if (is != null)
	            is.close();
	    }
	     
		return result;
	    
	}
    
/*    private HttpClient m_httpClient;
    private HttpPost m_httpPost;
    

    
	private JsonBase() {
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
	    HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

		m_httpClient = new DefaultHttpClient(httpParams);
		m_httpPost = new HttpPost();
	}

	public JSONObject doRequest(JSONObject jsonObject, URI uri) throws IOException, ClientProtocolException, JSONException{
		StringEntity stringEntity = new StringEntity(jsonObject.toString());
		stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, "application/json"));
		stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		m_httpPost.setEntity(stringEntity);
		m_httpPost.setURI(uri);
		
		HttpResponse httpResponse = m_httpClient.execute(m_httpPost);
		String resultStr = convertStreamToString(httpResponse.getEntity().getContent());
		JSONObject result = new JSONObject(resultStr);
		return result;
	}
	
	
    private String convertStreamToString(InputStream is) {
        
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        	Log.out.println(LOG, e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            	Log.out.println(LOG, e);
            }
        }
        return sb.toString();
    }
    
    public static String getString(JSONObject jsonObject, String key){
    	String result = null;
    	try {
			result = jsonObject.getString(key);
		} catch (JSONException e) {
			Log.out.println(LOG, e);
		}
		return result;
    } */   
}
