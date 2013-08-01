/**
 * 
 */
package ru.spb.osll.utils;

import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;

/**
 * @author Mark Zaslavskiy
 *
 */
public class LocationUtil {

	
	public static QualifiedCoordinates getCoordinates(){
		
		Location location = getLocation();
		if (location == null)
			return null;
		
		return location.getQualifiedCoordinates();
        
	}
	
	public static Location getLocation(){
		if (!isLocationSupported()){
			return null;
		}
		
		Criteria criteria = new Criteria();
        criteria.setHorizontalAccuracy(500);
        LocationProvider locationProvider;
		try {
			locationProvider = LocationProvider.getInstance(criteria);
			Location location = locationProvider.getLocation(10);	        
	        return location;
		} catch (Exception e) {
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
