package registrationScheduler.threadMgmt;

import registrationScheduler.store.Results;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.ISchedular;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;
/**
 * 
 * @author ashishpateria
 *
 */
public class CreateWorkers {
	private FileProcessor fileProcessor;
	private Results results;
	private ISchedular scheduler;
	/**
	 * 
	 * @param fileProcessor
	 * @param results
	 * @param scheduler
	 */
	public CreateWorkers(FileProcessor fileProcessorIn, Results resultsIn, ISchedular schedulerIn) {
		fileProcessor=fileProcessorIn;
		results=resultsIn;
		scheduler=schedulerIn;
		Logger.writeMessage("Create Worker Constructor called.", DebugLevel.CONSTRUCTOR);
	}
	
	/**
	 * This method creates threads, starts them and joins them
	 * @param numThreads
	 */
	public void startWorkers(int numThreads){
		Thread thread=null;
		for(int i=0;i<numThreads;i++){
			thread= new Thread(new WorkerThread(fileProcessor, results, scheduler));
			thread.start();
		}
		for(int i=0;i<numThreads;i++){
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println("Exception occured in startWorker method of CreateWorker class.");
				e.printStackTrace();
				System.exit(1);
			}
			
		}
		
	}

}
