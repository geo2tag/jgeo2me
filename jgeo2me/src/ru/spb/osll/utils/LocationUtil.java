/**
 * 
 */
package ru.spb.osll.utils;

import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;

/**
 * @author user
 *
 */
public class LocationUtil {

	
	public static QualifiedCoordinates getCoordinates(){
		
		if (!isLocationSupported()){
			return null;
		}
		
		Criteria cr = new Criteria();
        cr.setHorizontalAccuracy(500);
        LocationProvider lp;
		try {
			lp = LocationProvider.getInstance(cr);
			Location l = lp.getLocation(10);
			QualifiedCoordinates qc = l.getQualifiedCoordinates();
	        
	        return qc;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
	}
	
	private static boolean isLocationSupported() {
        boolean isItTrue = true;
            try {
                Class.forName("javax.microedition.location.Location");
            } catch (Exception e) {
                isItTrue = false;
            }
        return isItTrue;
    }
}
