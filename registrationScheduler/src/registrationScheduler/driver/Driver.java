package registrationScheduler.driver;

import registrationScheduler.store.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.ISchedular;
import registrationScheduler.util.Scheduler;
import registrationScheduler.util.Logger.DebugLevel;
import registrationScheduler.util.Logger;

/**
 * Driver classs
 * @author ashishpateria
 * main method creates objects of results, scheduler and file processor classes respectively.
 */
public class Driver {

	public Driver() {
		Logger.writeMessage("Driver Constructor called.", DebugLevel.CONSTRUCTOR);
	}

	public static void main(String[] args) {
		
		try{
			//arguments validation
		if(args.length!=5){
			System.err.println("Please provide 5 argument: reg-preference.txt,add-drop.txt,output.txt,NUM_THREADS,DEBUG_VALUE");
			System.exit(1);
		}
		//no. of threads validation
		int NUM_THREADS = Integer.parseInt(args[3]);
		if (!(NUM_THREADS >= 1 && NUM_THREADS <= 3)) {
			System.err.println("Number of threads must be between 1 to 3.");
			System.exit(1);
		}
		// validating debug value
		int DEBUG_VALUE = Integer.parseInt(args[4]);
		if (DEBUG_VALUE >= 0 && DEBUG_VALUE <= 4) {
			Logger.setDebugValue(DEBUG_VALUE);
		} else {
			System.err.println("Debug Value must be between 0 to 4.");
			System.exit(1);
		}
		
		FileProcessor fProcessor = new FileProcessor(args[0], args[1],args[2]);
		Results rs = new Results();
		ISchedular scheduler = new Scheduler();
		CreateWorkers cw = new CreateWorkers(fProcessor, rs, scheduler);
		cw.startWorkers(NUM_THREADS);
		rs.writeSchedulesToFile(args[2]);
		rs.writeSchedulesToScreen();
		
		}catch(Exception e){
			System.err.println("Error in file handling !!!");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public String toString() {
		return "Driver obkect toString method";
	}

	
}

