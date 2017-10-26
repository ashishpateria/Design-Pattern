package spreadsheetUpdates.util;

/**
 * Logger Class
 * @author ashishpateria
 *
 */

public class Logger {

	public Logger() {
		Logger.writeMessage("Loggers Contructor called ", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * enum for debug values
	 * @author ashishpateria
	 *
	 */
	public static enum DebugLevel{
		CYCLEPRESENT,CELLCREATED,RUN,CONSTRUCTOR,NOOUTPUT 
	};
	private static DebugLevel DEBUG_VALUE;
	
	public static void setDebugValue(int inputLevel){
		if(inputLevel==0)
			DEBUG_VALUE=DebugLevel.NOOUTPUT;
		if(inputLevel==1)	
			DEBUG_VALUE=DebugLevel.CYCLEPRESENT;
		if(inputLevel==2)	
			DEBUG_VALUE=DebugLevel.CELLCREATED;
		if(inputLevel==3)	
			DEBUG_VALUE=DebugLevel.RUN;
		else if(inputLevel==4)
			DEBUG_VALUE=DebugLevel.CONSTRUCTOR;
		//else
			//DEBUG_VALUE=DebugLevel.NOOUTPUT;
		
	}
	/**
	 * function to write message
	 * @param message
	 * @param levelIn
	 */
	public static void writeMessage(String message,DebugLevel levelIn){
		if(levelIn==DEBUG_VALUE){
			System.out.println(message);
		}
	}
	
	/**
	 * Logger toString method
	 */
	@Override
	public String toString() {
		return "Debug Level is " + DEBUG_VALUE;
	}

}
