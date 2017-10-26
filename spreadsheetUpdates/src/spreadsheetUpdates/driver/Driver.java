package spreadsheetUpdates.driver;

import spreadsheetUpdates.observer.SpreadSheetExample;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.Logger.DebugLevel;
/**
 * Driver class
 * @author ashishpateria
 *
 */
public class Driver {

	public Driver() {
		Logger.writeMessage("Driver Contructor called ", DebugLevel.CONSTRUCTOR);
	}


	public static void main(String[] args) {
		try{
			//Arguments Validation
			if(args.length!=3){
				System.err.println("Please provide 3 argument: input.txt,output.txt,DEBUG_VALUE");
				System.exit(1);
			}
			//Debug value validation
			int DEBUG_VALUE = Integer.parseInt(args[2]);
			if(DEBUG_VALUE >= 0 && DEBUG_VALUE <= 4){
				Logger.setDebugValue(DEBUG_VALUE);
			}
			else{
				System.err.println("Debug Value must be between 0 to 4.");
				System.exit(1);
			}
			
			FileProcessor fProcessor = new FileProcessor(args[0],args[1]);
			SpreadSheetExample spreadsheet=new SpreadSheetExample(fProcessor);
			spreadsheet.evaluation();
			spreadsheet.writeSchedulesToFile(args[1]);
		}catch(Exception e){
			System.err.println("Error in file handling !!!");
			e.printStackTrace();
			System.exit(1);
		}
		

	}
	
	/**
	 * Driver Class toString Method
	 */
	@Override
	public String toString() {
		return "Driver object toString method";
	}

}


