/**
 * 
 */
package ru.spb.osll.utils;

import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
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
		

		try {
			LocationProvider locationProvider = getLocationProvider();
			
			Location location = locationProvider.getLocation(60);	    
	        return location;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static LocationProvider getLocationProvider(){
		LocationProvider locationProvider = null;

			Criteria criteria = new Criteria();
			//criteria.setHorizontalAccuracy(500);
	        criteria.setPreferredPowerConsumption(Criteria.NO_REQUIREMENT);
	        criteria.setSpeedAndCourseRequired(false);
	        criteria.setAltitudeRequired(false);
	        criteria.setAddressInfoRequired(false);
	        
	        try {
				locationProvider = LocationProvider.getInstance(criteria);
			} catch (LocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return locationProvider;
	}
	
	
	
	private static boolean isClassSupported(String className){
		        boolean isItTrue = true;
            try {
                Class.forName(className);
            } catch (Exception e) {
                isItTrue = false;
            }
        return isItTrue;
	}
	
	public static boolean isLocationSupported() {
		return isClassSupported("javax.microedition.location.Location");
    }
	
	public static boolean isLocationUtilSupported(){
		return isClassSupported("com.nokia.mid.location.LocationUtil");
	}
}
