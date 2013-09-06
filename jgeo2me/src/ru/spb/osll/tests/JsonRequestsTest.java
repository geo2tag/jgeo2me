package ru.spb.osll.tests;

import java.util.Vector;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;



import ru.spb.osll.json.Errno;
import ru.spb.osll.json.JsonAddUserRequest;
import ru.spb.osll.json.JsonAddUserResponse;
import ru.spb.osll.json.JsonAlterChannelRequest;
import ru.spb.osll.json.JsonAlterChannelResponse;
import ru.spb.osll.json.JsonApplyChannelRequest;
import ru.spb.osll.json.JsonApplyChannelResponse;
import ru.spb.osll.json.JsonApplyMarkRequest;
import ru.spb.osll.json.JsonApplyMarkResponse;
import ru.spb.osll.json.JsonAvailableChannelsRequest;
import ru.spb.osll.json.JsonAvailableChannelsResponse;
import ru.spb.osll.json.JsonFilterBoxRequest;
import ru.spb.osll.json.JsonFilterCircleRequest;
import ru.spb.osll.json.JsonFilterCylinderRequest;
import ru.spb.osll.json.JsonFilterFenceRequest;
import ru.spb.osll.json.JsonFilterPolygonRequest;
import ru.spb.osll.json.JsonFilterRectangleRequest;
import ru.spb.osll.json.JsonFilterResponse;
import ru.spb.osll.json.JsonLoadTagsRequest;
import ru.spb.osll.json.JsonLoadTagsResponse;
import ru.spb.osll.json.JsonLoginRequest;
import ru.spb.osll.json.JsonLoginResponse;
import ru.spb.osll.json.JsonRequestException;
import ru.spb.osll.json.JsonSubscribeRequest;
import ru.spb.osll.json.JsonSubscribeResponse;
import ru.spb.osll.json.JsonSubscribedChannelsRequest;
import ru.spb.osll.json.JsonUnsubscribeRequest;
import ru.spb.osll.json.JsonUnsubscribeResponse;
import ru.spb.osll.json.JsonVersionRequest;
import ru.spb.osll.json.JsonVersionResponse;
import ru.spb.osll.json.RequestSender;
import ru.spb.osll.log.Log;
import ru.spb.osll.objects.Channel;
import jmunit.framework.cldc11.TestCase;

public class JsonRequestsTest extends TestCase {

	private static final double TEST_LAT = 30.;
	private static final double TEST_LON = 60.;
	private static final double TEST_RAD = 99999.;
	
	private static final String TEST_NEW_USER_EMAIL = "test_user_email@example.ru";
	private static final String TEST_NEW_USER_PASSWORD = "test";
	private static final String TEST_NEW_USER_LOGIN = "test_user";
	
	private static final Integer TEST_NEW_CHANNEL_ACTIVE_RADIUS = new Integer(10);
	private static final String TEST_NEW_CHANNEL_URL = "";
	private static final String TEST_NEW_CHANNEL_DESCRIPTION = "test_channel";
	private static final String TEST_NEW_CHANNEL_NAME = "testChannel";
	
	private static final String TEST_CHANNEL = "testChannel";
	private static final String TEST_TITLE = "testTitle";
	private static final String TEST_LINK = "testLink";
	private static final double TEST_NEW_MARK_LON = 30.0;
	private static final double TEST_NEW_MARK_LAT = 60.0;
	private static final String TEST_DESCRIPTION = "testDecription";
	private static final double TEST_NEW_MARK_ALT = 0;
	private static final String TEST_NEW_MARK_TIME = "28 04 2013 06:26:40.631";
	
	private static final double TEST_BOX_LAT1 = 0;
	private static final double TEST_BOX_LAT2 = 100;
	private static final double TEST_BOX_LON1 = 0;
	private static final double TEST_BOX_LON2 = 100;
	private static final double TEST_BOX_ALT1 = 0;
	private static final double TEST_BOX_ALT2 = 100;
	private static final String TEST_BOX_TIME_FROM = "28 04 2000 06:26:40.631";
	private static final String TEXT_BOX_TIME_TO = "28 04 2023 06:26:40.631";
	
	
	private static final double TEST_CIRCLE_LAT = 30;
	private static final double TEST_CIRCLE_LON = 60;
	private static final double TEST_CIRCE_RAD = 999;
	private static final String TEST_CIRCLE_TIME_FROM = "28 04 2000 06:26:40.631";
	private static final String TEXT_CIRCLE_TIME_TO = "28 04 2020 06:26:40.631";
	
	private static final double TEST_CYLINDER_LON = 30;
	private static final double TEST_CYLINDER_LAT = 60;
	private static final double TEST_CYLINDER_RAD = 999;
	private static final double TEST_CYLINDER_ALT1 = 0;
	private static final double TEST_CYLINDER_ALT2 = 1000;
	private static final String TEST_CYLINDER_TIME_FROM = "28 04 2000 06:26:40.631";
	private static final String TEXT_CYLINDER_TIME_TO = "28 04 2020 06:26:40.631";
	
	
	
	private static final double TEST_FENCE_LAT1 = 0;
	private static final double TEST_FENCE_LON1 = 0;
	
	private static final double TEST_FENCE_LON2 = 0;
	private static final double TEST_FENCE_LAT2 = 100;
	
	private static final double TEST_FENCE_LAT3 = 100;
	private static final double TEST_FENCE_LON3 = 100;
	
	private static final double TEST_FENCE_LAT4 = 100;
	private static final double TEST_FENCE_LON4 = 0;
	
	private static final double TEST_FENCE_ALT1 = 0;
	private static final double TEST_FENCE_ALT2 = 100;
	private static final String TEST_FENCE_TIME_FROM = "28 04 2000 06:26:40.631";
	private static final String TEST_FENCE_TIME_TO = "28 04 2020 06:26:40.631";
	
	private static final double TEST_POLYGON_LAT1 = 0;
	private static final double TEST_POLYGON_LON1 = 0;
	
	private static final double TEST_POLYGON_LAT2 = 0;
	private static final double TEST_POLYGON_LON2 = 100;
	
	private static final double TEST_POLYGON_LON3 = 100;
	private static final double TEST_POLYGON_LAT3 = 100;
	
	private static final double TEST_POLYGON_LON4 = 100;
	private static final double TEST_POLYGON_LAT4 = 0;
	private static final String TEST_POLYGON_TIME_FROM = "28 04 2000 06:26:40.631";
	private static final String TEST_POLYGON_TIME_TO = "28 04 2020 06:26:40.631";
	
	private static final double TEST_RECT_LAT1 = 0;
	private static final double TEST_RECT_LAT2 = 100;
	private static final double TEST_RECT_LON1 = 0;
	private static final double TEST_RECT_LON2 = 100;
	private static final String TEST_RECT_TIME_FROM = "28 04 2000 06:26:40.631";
	private static final String TEST_RECT_TIME_TO = "28 04 2020 06:26:40.631";
	
	private static final String TEST_SUBSCRIBE_CHANNEL = "Sales";
	
	private static final String TEST_UNSUBSCRIBE_CHANNEL = "Sales";
	
	
	private static final String TEST_JSON_INT_KEY = "testIntKey";
	private static final String TEST_JSON_DOUBLE_KEY = "testDoubleKey";
	private static final String TEST_JSON_STRING_KEY = "testStringKey";
	
	private static final int TEST_JSON_INT_VALUE = 12345 ;
	private static final double TEST_JSON_DOUBLE_VALUE = 123.456;
	private static final String TEST_JSON_STRING_VALUE = "testString";
	private static final String TEST_JSON_CORRECT_STRING = 
			"{\"testIntKey\":12345,\"testArrayKey\":[0,1,2],\"testObjectKey\":{\"testIntKey\":12345,\"testDoubleKey\":123.456,\"testStringKey\":\"testString\"},\"testDoubleKey\":123.456,\"testStringKey\":\"testString\"}";
	private static final String TEST_JSON_OBJECT_KEY = "testObjectKey";
	private static final String TEST_JSON_ARRAY_KEY = "testArrayKey";
	private static final int TEST_JSON_ARRAY_SIZE = 3;
		
	private static final String TEST_LOGIN = "Paul";
	private static final String TEST_PASSWORD = "test";
	
	private static final String TEST_SERVER = "http://194.85.173.9:20005/service";
	private static final String TEST_LOG = "JMEGEO_TEST";
	
	private static final String TEST_ALTER_CHANNEL_FIELD = "url";
	private static final String TEST_ALTER_CHANNEL_VALUE = "test_url";
	private static final String TEST_ALTER_CHANNEL_NAME = "Rom";


	private String m_authToken = "";
	
	/**
	 * The default constructor. It just transmits the necessary informations to
	 * the superclass.
	 * 
	 * @param totalOfTests the total of test methods present in the class.
	 * @param name this testcase's name.
	 */
	public JsonRequestsTest() {
		super(19, "JsonRequestsTest");
		

	}

	
	
	private void testLoginRequest(){
		JsonLoginRequest req = new JsonLoginRequest
				(TEST_LOGIN, TEST_PASSWORD, TEST_SERVER);
		
		JsonLoginResponse res = new JsonLoginResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
		
		
		try {
			RequestSender.performRequest(req, res, errnos);
			Log.out.println(TEST_LOG, "Got auth_token: "+ res.getAuthString());
			m_authToken = res.getAuthString();
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
		
		
		
	}
	
	private void testLoadTagsRequest(){
		JsonLoadTagsRequest req = new JsonLoadTagsRequest
				(m_authToken, TEST_LAT, TEST_LON, TEST_RAD, TEST_SERVER);
		
		JsonLoadTagsResponse res = new JsonLoadTagsResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			
			Channel channel = (Channel)res.getChannels().elementAt(0);
			Vector marks = channel.getMarks();
			Log.out.println(TEST_LOG, "Got marks, size =  "+ marks.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	
	private void testAddUserRequest(){
		JsonAddUserRequest req = new JsonAddUserRequest
				(TEST_NEW_USER_LOGIN, TEST_NEW_USER_PASSWORD, TEST_NEW_USER_EMAIL, TEST_SERVER);
		
		JsonAddUserResponse res = new JsonAddUserResponse();
		int[] errnos = {Errno.SUCCESS.intValue(), Errno.USER_ALREADY_EXIST_ERROR.intValue(),
				Errno.EMAIL_ALREADY_EXIST_ERROR.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testApplyChannelRequest(){
		JsonApplyChannelRequest req = new JsonApplyChannelRequest
				(m_authToken,TEST_NEW_CHANNEL_NAME,TEST_NEW_CHANNEL_DESCRIPTION, 
						TEST_NEW_CHANNEL_URL, TEST_NEW_CHANNEL_ACTIVE_RADIUS,TEST_SERVER);
		
		JsonApplyChannelResponse res = new JsonApplyChannelResponse();
		int[] errnos = {Errno.SUCCESS.intValue(), Errno.CHANNEL_ALREADY_EXIST_ERROR.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testApplyMarkRequest(){
		JsonApplyMarkRequest req = new JsonApplyMarkRequest(
				m_authToken,TEST_CHANNEL, TEST_TITLE, TEST_LINK, 
				TEST_DESCRIPTION, TEST_NEW_MARK_LAT, TEST_NEW_MARK_LON,
				TEST_NEW_MARK_ALT, TEST_NEW_MARK_TIME, TEST_SERVER);
		//		(m_authToken, TEST_SERVER);
		
		JsonApplyMarkResponse res = new JsonApplyMarkResponse();
		int[] errnos = {Errno.SUCCESS.intValue(), Errno.CHANNEL_NOT_SUBCRIBED_ERROR.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testAvailableChannelsRequest(){
		JsonAvailableChannelsRequest req = new JsonAvailableChannelsRequest
				(m_authToken, TEST_SERVER);
		
		JsonAvailableChannelsResponse res = new JsonAvailableChannelsResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannels();
			Log.out.println(TEST_LOG, "Got available channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testFilterBoxRequest(){
		JsonFilterBoxRequest req = new JsonFilterBoxRequest(
				m_authToken, TEST_BOX_LAT1, TEST_BOX_LAT2,
				TEST_BOX_LON1, TEST_BOX_LON2, TEST_BOX_ALT1, 
				TEST_BOX_ALT2, TEST_BOX_TIME_FROM, TEXT_BOX_TIME_TO, TEST_SERVER);
				
		
		JsonFilterResponse res = new JsonFilterResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannelsData();
			Log.out.println(TEST_LOG, "Got filterBox channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testFilterCircleRequest(){
		JsonFilterCircleRequest req = new JsonFilterCircleRequest(
				m_authToken, TEST_CIRCLE_LAT, TEST_CIRCLE_LON,
				TEST_CIRCE_RAD, TEST_CIRCLE_TIME_FROM, TEXT_CIRCLE_TIME_TO, TEST_SERVER);
				
		
		JsonFilterResponse res = new JsonFilterResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannelsData();
			Log.out.println(TEST_LOG, "Got filterCircle channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testFilterCylinderRequest(){
		JsonFilterCylinderRequest req = new JsonFilterCylinderRequest(
				m_authToken, TEST_CYLINDER_LAT, TEST_CYLINDER_LON,
				TEST_CYLINDER_RAD, TEST_CYLINDER_ALT1, TEST_CYLINDER_ALT2, 
				TEST_CYLINDER_TIME_FROM, TEXT_CYLINDER_TIME_TO, TEST_SERVER);
				
		
		JsonFilterResponse res = new JsonFilterResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannelsData();
			Log.out.println(TEST_LOG, "Got filterCylinder channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testFilterFenceRequest(){
		JsonFilterFenceRequest req = new JsonFilterFenceRequest(
				m_authToken, TEST_FENCE_ALT1, TEST_FENCE_ALT2, 
				TEST_FENCE_TIME_FROM, TEST_FENCE_TIME_TO, TEST_SERVER);
		req.addPoint(TEST_FENCE_LAT1, TEST_FENCE_LON1);		
		req.addPoint(TEST_FENCE_LAT2, TEST_FENCE_LON2);	
		req.addPoint(TEST_FENCE_LAT3, TEST_FENCE_LON3);	
		req.addPoint(TEST_FENCE_LAT4, TEST_FENCE_LON4);	
		
		
		JsonFilterResponse res = new JsonFilterResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannelsData();
			Log.out.println(TEST_LOG, "Got filterFence channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testFilterPolygonRequest(){
		JsonFilterPolygonRequest req = new JsonFilterPolygonRequest(
				m_authToken, TEST_POLYGON_TIME_FROM, TEST_POLYGON_TIME_TO, TEST_SERVER);
		req.addPoint(TEST_POLYGON_LAT1, TEST_POLYGON_LON1);		
		req.addPoint(TEST_POLYGON_LAT2, TEST_POLYGON_LON2);	
		req.addPoint(TEST_POLYGON_LAT3, TEST_POLYGON_LON3);	
		req.addPoint(TEST_POLYGON_LAT4, TEST_POLYGON_LON4);			
		
		JsonFilterResponse res = new JsonFilterResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannelsData();
			Log.out.println(TEST_LOG, "Got filterPolygon channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testFilterRectangleRequest(){
		JsonFilterRectangleRequest req = new JsonFilterRectangleRequest(
				m_authToken, TEST_RECT_LAT1, TEST_RECT_LAT2, TEST_RECT_LON1,
				TEST_RECT_LON2, TEST_RECT_TIME_FROM, TEST_RECT_TIME_TO, TEST_SERVER);
				
		
		JsonFilterResponse res = new JsonFilterResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannelsData();
			Log.out.println(TEST_LOG, "Got filterRectangle channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testSubscribedChannelsRequest(){
		JsonSubscribedChannelsRequest req = new JsonSubscribedChannelsRequest
				(m_authToken, TEST_SERVER);
		
		JsonAvailableChannelsResponse res = new JsonAvailableChannelsResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			Vector channels = res.getChannels();
			Log.out.println(TEST_LOG, "Got subscribed channels, size =  "+ channels.size());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testSubscribeRequest(){
		JsonSubscribeRequest req = new JsonSubscribeRequest
				(m_authToken, TEST_SUBSCRIBE_CHANNEL, TEST_SERVER);
		
		JsonSubscribeResponse res = new JsonSubscribeResponse();
		int[] errnos = {Errno.SUCCESS.intValue(), Errno.CHANNEL_ALREADY_EXIST_ERROR.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testUnsubscribeRequest(){
		JsonUnsubscribeRequest req = new JsonUnsubscribeRequest
				(m_authToken, TEST_UNSUBSCRIBE_CHANNEL, TEST_SERVER);
		
		JsonUnsubscribeResponse res = new JsonUnsubscribeResponse();
		int[] errnos = {Errno.SUCCESS.intValue(), Errno.CHANNEL_NOT_SUBCRIBED_ERROR.intValue()};
				
		try {
			RequestSender.performRequest(req, res, errnos);
			
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void testVersionRequest(){
		JsonVersionRequest req = new JsonVersionRequest(TEST_SERVER);

		JsonVersionResponse res = new JsonVersionResponse();
		int[] errnos = {Errno.SUCCESS.intValue()};
				
		try {
			
			RequestSender.performRequest(req, res, errnos);
			Log.out.println(TEST_LOG, "Platform version "+ res.getVersionStr());
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
		
	
	/**
	 * This method stores all the test methods invocation. The developer must
	 * implement this method with a switch-case. The cases must start from 0 and
	 * increase in steps of one until the number declared as the total of tests
	 * in the constructor, exclusive. For example, if the total is 3, the cases
	 * must be 0, 1 and 2. In each case, there must be a test method invocation.
	 * 
	 * @param testNumber the test to be executed.
	 * @throws Throwable anything that the executed test can throw.
	 */
	public void test(int testNumber) throws Throwable {
		Log.out.println(TEST_LOG, "Doing tests");
		switch (testNumber) {
		case 0:{
			testLoginRequest();

			break;
		}
		case 1:{
			testLoadTagsRequest();
			break;
		}
		case 2:{
			testAddUserRequest();
			break;
		}
		case 3:{
			testApplyChannelRequest();
			break;
		}
		case 4:{
			testApplyMarkRequest();
			break;
		}
		case 5:{
			testAvailableChannelsRequest();
			break;
		}
		case 6:{
			testFilterBoxRequest();
			break;
		}
		case 7:{
			testFilterCircleRequest();
			break;
		}
		case 8:{
			testFilterCylinderRequest();
			break;
		}
		case 9:{
			testFilterFenceRequest();
			break;
		}
		case 10:{
			testFilterPolygonRequest();
			break;
		}
		case 11:{
			testFilterRectangleRequest();
			break;
		}
		case 12:{
			testSubscribedChannelsRequest();
			break;
		}
		case 13:{
			testSubscribeRequest();
			break;
		}
		case 14:{
			testUnsubscribeRequest();
			break;
		}
		case 15:{
			testVersionRequest();
			break;
		}
		case 16:{
			testJsonGeneration();
			break;
		}
		case 17:{
			testJsonParsing();
			break;
		}
		case 18:
			testJsonAlterChannel();
			break;

		}
	}



	private void testJsonParsing() {
		// TODO Auto-generated method stub
		try{
			JSONObject testObject = new JSONObject(TEST_JSON_CORRECT_STRING);
			
			assertEquals(testObject.getInt(TEST_JSON_INT_KEY),TEST_JSON_INT_VALUE);
			assertEquals(testObject.getDouble(TEST_JSON_DOUBLE_KEY),TEST_JSON_DOUBLE_VALUE);
			assertEquals(testObject.getString(TEST_JSON_STRING_KEY),TEST_JSON_STRING_VALUE);
			
			JSONObject tmpObject = testObject.getJSONObject(TEST_JSON_OBJECT_KEY);
			
			assertNotNull(tmpObject);
			
			assertEquals(tmpObject.getInt(TEST_JSON_INT_KEY),TEST_JSON_INT_VALUE);
			assertEquals(tmpObject.getDouble(TEST_JSON_DOUBLE_KEY),TEST_JSON_DOUBLE_VALUE);
			assertEquals(tmpObject.getString(TEST_JSON_STRING_KEY),TEST_JSON_STRING_VALUE);
			
			JSONArray tmpArray = testObject.getJSONArray(TEST_JSON_ARRAY_KEY);
			
			assertNotNull(tmpArray);
			
			for (int i=0; i<TEST_JSON_ARRAY_SIZE; i++){
				Log.out.println(TEST_LOG, tmpArray.get(i).toString()+" "+tmpArray.get(i).getClass().getName());
				//assertEquals(tmpArray.getInt(i),i);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
			
		}
	}



	private void testJsonGeneration() {
		// TODO Auto-generated method stub
		JSONObject testObject = new JSONObject();
		JSONObject tmpObject = new JSONObject();
		JSONArray tmpArray = new JSONArray();
		
		try {
			testObject.put(TEST_JSON_INT_KEY, TEST_JSON_INT_VALUE);
			testObject.put(TEST_JSON_DOUBLE_KEY, TEST_JSON_DOUBLE_VALUE);
			testObject.put(TEST_JSON_STRING_KEY, TEST_JSON_STRING_VALUE);
			
			
			tmpObject.put(TEST_JSON_INT_KEY, TEST_JSON_INT_VALUE);
			tmpObject.put(TEST_JSON_DOUBLE_KEY, TEST_JSON_DOUBLE_VALUE);
			tmpObject.put(TEST_JSON_STRING_KEY, TEST_JSON_STRING_VALUE);
			
			testObject.put(TEST_JSON_OBJECT_KEY, tmpObject);
			
			for (int i=0; i<TEST_JSON_ARRAY_SIZE; i++){
				tmpArray.put(i);
			}
			
			testObject.put(TEST_JSON_ARRAY_KEY, tmpArray);
			
		//	Log.out.println(TEST_LOG, "Generated JSON " + testObject.toString());
			assertEquals(TEST_JSON_CORRECT_STRING, testObject.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
			e.printStackTrace();
		}
		
		
		//testObject.put(TEST_JSON_OBJECT_KEY, TEST_JSON__VALUE);
		
	}

	private void testJsonAlterChannel(){
		JsonAlterChannelRequest req = new JsonAlterChannelRequest
				(m_authToken, TEST_ALTER_CHANNEL_NAME, TEST_ALTER_CHANNEL_FIELD, TEST_ALTER_CHANNEL_VALUE, TEST_SERVER);
		
		JsonAlterChannelResponse res = new JsonAlterChannelResponse();
		int[] errnos = {Errno.USER_DOES_NOT_OWN_CHANNEL_ERROR.intValue()};
		
		
		try {
			RequestSender.performRequest(req, res, errnos);
		} catch (JsonRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}

	}

}
