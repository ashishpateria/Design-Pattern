package registrationScheduler.util;

//import registrationScheduler.util.Logger.DebugLevel;

/**
 * 
 * @author ashishpateria
 *
 */
public class Logger {

	public Logger() {
		Logger.writeMessage("Logger's Constructor called.", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * enum for debuglevel values
	 * @author ashishpateria
	 *
	 */
	public static enum DebugLevel {
		CONSTRUCTOR, RUN, RESULT, CONTENTSATEACHENTRY, NOOUTPUT
	};
	private static DebugLevel DEBUG_VALUE;
	
	/**
	 * sets debug levels for different debug values. 
	 * @param inputLevel
	 */
	public static void setDebugValue(int inputLevel) {
		if (inputLevel == 0)
			DEBUG_VALUE = DebugLevel.NOOUTPUT;
		else if (inputLevel == 1)
			DEBUG_VALUE = DebugLevel.CONTENTSATEACHENTRY;
		else if (inputLevel == 2)
			DEBUG_VALUE = DebugLevel.RESULT;
		else if (inputLevel == 3)
			DEBUG_VALUE = DebugLevel.RUN;
		else if (inputLevel == 4)
			DEBUG_VALUE = DebugLevel.CONSTRUCTOR;
		else
			DEBUG_VALUE = DebugLevel.NOOUTPUT;
	}
	
	/**
	 *  writes message to screen according to the debug value.
	 * @param message
	 * @param levelIn
	 */
	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == DEBUG_VALUE)//&& DEBUG_VALUE != DebugLevel.NOOUTPUT)
			System.out.println(message);
	}
	
	/**
	 * toSring method
	 */
	@Override
	public String toString() {
		return "Debug Level is " + DEBUG_VALUE;
	}
}
