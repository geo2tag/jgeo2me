package ru.spb.osll.gui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 * This class implements general three buttons dialog with ability to add user-defined behavior to buttons.
 * @author Mark Zaslavskiy
 *
 */

public class ConfirmationAlert{
	
	
	
	public interface ConfirmationAlertAction{
			public void action();
	}
	
	
	private Alert m_alert;
	private Display m_display;
	private Displayable m_displayable;
	
	private String m_negativeText;
	private String m_positiveText;
	private String m_middleText;
		
	private ConfirmationAlertAction m_yesAction = null;
	private ConfirmationAlertAction m_noAction = null;
	private ConfirmationAlertAction m_middleAction = null;
	
	public ConfirmationAlert(Display display, Displayable displayable, String title, String text, 
			String positiveText, String negativeText, String middleText){
		
		m_display = display;
		m_displayable = displayable;
		
		m_negativeText = negativeText;
		m_positiveText = positiveText;
		m_middleText = middleText;
		
		m_alert = new Alert(title, text, null, AlertType.CONFIRMATION);
		m_alert.addCommand(new Command(m_middleText, Command.OK, 1));
		m_alert.addCommand(new Command (m_positiveText, Command.HELP, 1));
		m_alert.addCommand(new Command(m_negativeText, Command.CANCEL, 1));
		
		
		
		m_alert.setCommandListener(new CommandListener() {
			
			public void commandAction(Command arg0, Displayable arg1) {
				// TODO Auto-generated method stub
				if (arg0.getLabel().equals(m_positiveText) && m_yesAction != null) {
					m_yesAction.action();
					closeAlert();
				}
				
				if (arg0.getLabel().equals(m_negativeText) && m_noAction != null) {
					m_noAction.action();
					closeAlert();
				}
				
				if (arg0.getLabel().equals(m_middleText) && m_middleAction != null) {
					m_middleAction.action();
				}
				
			}
		});
	}
	
	public void setOnYesAction(ConfirmationAlertAction yesAction){
		m_yesAction = yesAction;
	}
	
	public void setOnNoAction(ConfirmationAlertAction noAction){
		m_noAction = noAction;
	}
	
	public void setOnMiddleAction(ConfirmationAlertAction middleAction){
		m_middleAction = middleAction;
	}
	
	private void closeAlert(){
		m_display.setCurrent(m_displayable);
	}
	
	public void showAlert(){
		System.out.println(m_alert.toString() + " " + (m_display == null) );
		
		m_display.setCurrent(m_alert, m_display.getCurrent());
	}
}