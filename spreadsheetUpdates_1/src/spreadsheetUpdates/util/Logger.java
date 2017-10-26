package spreadsheetUpdates.util;



public class Logger {

	public Logger() {
		Logger.writeMessage("Loggers Contructor called ", DebugLevel.CONSTRUCTOR);
	}
	public static enum DebugLevel{
		CONSTRUCTOR, RUN, RESULT, CONTENTSATEACHENTRY, NOOUTPUT
	};
	private static DebugLevel DEBUG_VALUE;
	
	public static void setDebugValue(int inputLevel){
		if(inputLevel==0)
			DEBUG_VALUE=DebugLevel.NOOUTPUT;
		else if(inputLevel==4)
			DEBUG_VALUE=DebugLevel.CONSTRUCTOR;
		else
			DEBUG_VALUE=DebugLevel.NOOUTPUT;
		
	}
	public static void writeMessage(String message,DebugLevel levelIn){
		if(levelIn==DEBUG_VALUE){
			System.out.println(message);
		}
	}
	
	@Override
	public String toString() {
		return "Debug Level is " + DEBUG_VALUE;
	}

}
