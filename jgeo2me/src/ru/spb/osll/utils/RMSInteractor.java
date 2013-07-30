/**
 * 
 */
package ru.spb.osll.utils;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 * This class handles interaction with RMS.
 * @author Mark Zaslavskiy
 *
 */
public class RMSInteractor {

	
	public static String getRecord(String key){
		try {	
			RecordStore recordStore = RecordStore.openRecordStore(key, false);	
			RecordEnumeration recEnum = recordStore.enumerateRecords( null, null, false );

			byte[] bytes = recEnum.nextRecord();
			recEnum.destroy();

			recordStore.closeRecordStore();
			System.out.println("Succsesful retrival of key = "+key+" from RMS");
			String result = new String(bytes);
			return result;
		}  catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erorrs during retrival of key = "+key+" from RMS");
			return null;
		}
	}
	
	public static void createRecord(String key,  String value){
		try {
			RecordStore recordStore = RecordStore.openRecordStore(key, true);
			byte[] data = value.getBytes();
			recordStore.addRecord(data, 0, data.length);		
			recordStore.closeRecordStore();

			System.out.println("Record for "+ key+ " was created successfuly");
		}  catch (RecordStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Record for "+ key+ " was not created");
		}
	}
	
	private static void setRecord(String key,  String value){
		try {	
			RecordStore recordStore = RecordStore.openRecordStore(key, true);

			RecordEnumeration recEnum = recordStore.enumerateRecords( null, null, false );

			byte[] data = value.getBytes();
			
			recordStore.setRecord(recEnum.nextRecordId(),data, 0, data.length);
			recEnum.destroy();

			recordStore.closeRecordStore();

			System.out.println("Succsesful write of record for key = "+key);
		}  catch (RecordStoreException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			System.out.println("Errors during write of record for key = "+key);

		}
	}
}
