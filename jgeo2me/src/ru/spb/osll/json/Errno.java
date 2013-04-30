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

import java.util.Hashtable;



public class Errno {

    public static Integer SUCCESS                           = new Integer(0);
    public static Integer WRONG_TOKEN_ERROR                 = new Integer(1);
    public static Integer USER_ALREADY_EXIST_ERROR          = new Integer(2);
    public static Integer USER_DOES_NOT_EXIST_ERROR         = new Integer(3);
    public static Integer CHANNEL_ALREADY_EXIST_ERROR       = new Integer(4);
    public static Integer CHANNEL_DOES_NOT_EXIST_ERROR      = new Integer(5);
    public static Integer SUBSCRIPTION_ALREADY_EXIST        = new Integer(6);
    public static Integer INTERNAL_DB_ERROR                 = new Integer(7);
    public static Integer INCORRECT_QUERY_NAME_ERROR        = new Integer(8);
    public static Integer INCORRECT_JSON_ERROR              = new Integer(9);
    public static Integer INCORRECT_CREDENTIALS_ERROR       = new Integer(10);
    public static Integer CHANNEL_NOT_SUBCRIBED_ERROR       = new Integer(11);
    public static Integer CHANNEL_ALREADY_SUBSCRIBED_ERROR  = new Integer(12);
    public static Integer TAG_DOES_NOT_EXIST_ERROR          = new Integer(13);
    public static Integer TAG_ALREADY_EXIST_ERROR           = new Integer(14);
    public static Integer NULL_TIMESLOT_ERROR               = new Integer(15);
    public static Integer UNKNOWN_ERROR                     = new Integer(16);
    public static Integer TMP_USER_ALREADY_EXIST_ERROR      = new Integer(17);
    public static Integer NETWORK_ERROR                     = new Integer(18);
    public static Integer EMAIL_ALREADY_EXIST_ERROR         = new Integer(19);
    public static Integer WEAK_PASSWORD_ERROR	        = new Integer(20);
    public static Integer NOT_SUPPORTED         		= new Integer(21);
    public static Integer DB_DOES_NOT_EXIST_ERROR         	= new Integer(22);

    private static Hashtable emap = new Hashtable();
    static {
        emap.put(SUCCESS,                           "SUCCESS");                         // 0
        emap.put(WRONG_TOKEN_ERROR,                 "WRONG_TOKEN_ERROR");               // 1
        emap.put(USER_ALREADY_EXIST_ERROR,          "USER_ALREADY_EXIST_ERROR");        // 2
        emap.put(USER_DOES_NOT_EXIST_ERROR,         "USER_DOES_NOT_EXIST_ERROR");       // 3
        emap.put(CHANNEL_ALREADY_EXIST_ERROR,       "CHANNEL_ALREADY_EXIST_ERROR");     // 4
        emap.put(CHANNEL_DOES_NOT_EXIST_ERROR,      "CHANNEL_DOES_NOT_EXIST_ERROR");    // 5
        emap.put(SUBSCRIPTION_ALREADY_EXIST,        "SUBSCRIPTION_ALREADY_EXIST");      // 6
        emap.put(INTERNAL_DB_ERROR,                 "INTERNAL_DB_ERROR");               // 7
        emap.put(INCORRECT_QUERY_NAME_ERROR,        "INCORRECT_QUERY_NAME_ERROR");      // 8
        emap.put(INCORRECT_JSON_ERROR,              "INCORRECT_JSON_ERROR");            // 9
        emap.put(INCORRECT_CREDENTIALS_ERROR,       "INCORRECT_CREDENTIALS_ERROR");     // 10
        emap.put(CHANNEL_NOT_SUBCRIBED_ERROR,       "CHANNEL_NOT_SUBCRIBED_ERROR");     // 11
        emap.put(CHANNEL_ALREADY_SUBSCRIBED_ERROR,  "CHANNEL_ALREADY_SUBSCRIBED_ERROR");// 12
        emap.put(TAG_DOES_NOT_EXIST_ERROR,          "TAG_DOES_NOT_EXIST_ERROR");        // 13
        emap.put(TAG_ALREADY_EXIST_ERROR,           "TAG_ALREADY_EXIST_ERROR");         // 14
        emap.put(NULL_TIMESLOT_ERROR,               "NULL_TIMESLOT_ERROR");             // 15
        emap.put(UNKNOWN_ERROR,                     "UNKNOWN_ERROR");                   // 16
        emap.put(TMP_USER_ALREADY_EXIST_ERROR,      "TMP_USER_ALREADY_EXIST_ERROR");    // 17
        emap.put(NETWORK_ERROR,                     "NETWORK_ERROR");                   // 18
        emap.put(EMAIL_ALREADY_EXIST_ERROR,         "EMAIL_ALREADY_EXIST_ERROR");       // 19
        emap.put(WEAK_PASSWORD_ERROR,		    "WEAK_PASSWORD_ERROR");       	// 20
        emap.put(NOT_SUPPORTED,			    "NOT_SUPPORTED");       		// 21
        emap.put(DB_DOES_NOT_EXIST_ERROR,           "DB_DOES_NOT_EXIST_ERROR");       	// 22
    }
	
	public static String getErrorByCode(int error) {
		return (String) emap.get(new Integer(error));
	}

}
