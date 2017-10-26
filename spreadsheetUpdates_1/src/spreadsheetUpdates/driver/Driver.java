package spreadsheetUpdates.driver;

import spreadsheetUpdates.observer.SpreadsheetExample;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.Logger.DebugLevel;

public class Driver {

	public Driver() {
		Logger.writeMessage("Driver Contructor called ", DebugLevel.CONSTRUCTOR);
	}

	public static void main(String[] args) {
		
		try{
			if(args.length!=2){
				System.err.println("Please provide 5 argument: reg-preference.txt,add-drop.txt,output.txt,NUM_THREADS,DEBUG_VALUE");
				System.exit(1);
			}
			FileProcessor fProcessor = new FileProcessor(args[0],args[1]);
			SpreadsheetExample spreadsheet=new SpreadsheetExample(fProcessor);
			spreadsheet.evaluation();
			
		}catch(Exception e){
			System.err.println("Error in file handling !!!");
			e.printStackTrace();
			System.exit(1);
		}
		

	}

	@Override
	public String toString() {
		return "Driver object toString method";
	}
}
