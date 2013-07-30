/**
 * 
 */
package ru.spb.osll.utils;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.midlet.MIDlet;


/**
 * This class does nothing except openning URL in default browser.
 * @author Mark Zaslavskiy
 *
 */
public class UrlOpener {

	public static void openURL(MIDlet midlet, String URL){
		boolean mustExit = false;
	    try {
	        mustExit = midlet.platformRequest(URL);
	    } catch (ConnectionNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    if(mustExit){
	        midlet.notifyDestroyed();
	    }
	}
}
