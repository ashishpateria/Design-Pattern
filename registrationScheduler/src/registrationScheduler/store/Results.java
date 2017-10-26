package registrationScheduler.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import registrationScheduler.util.Logger;
import registrationScheduler.util.Student;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * 
 * @author ashishpateria
 *
 */
public class Results implements FileDisplayInterface,StdoutDisplayInterface {
	//Vector list of students
	public List<Student> allocationStore = new Vector<Student>();
	private static BufferedWriter writer;
	
	public Results() {
		Logger.writeMessage("Results Constructor called.", DebugLevel.CONSTRUCTOR);
	
	}
	
	/**
	 * This method adds the result entry of each student into allocationStore vector
	 * @param s
	 */
	public void resultAllocationStore(Student s) {
		// add allocated student object from here
		allocationStore.add(s);
		Logger.writeMessage(s.getName() + " added to the Results.", DebugLevel.RESULT);
		

	}
	/**
	 * Writes schedules to screen
	 */
	public void writeSchedulesToScreen() {
		// TODO Auto-generated method stub
		for (Student s : allocationStore) {
			Logger.writeMessage(s.getName() + " " + s.getAllocatedCourseFirst() + " " + s.getAllocatedCourseSecond()
			+ " " + s.getAllocatedCourseThird() + " " + s.getAllocatedCourseFourth()+ " " +
			s.getAllocatedCourseFifth()+ " " +s.getTotalPrefScore()+"", DebugLevel.CONTENTSATEACHENTRY);

		}
		Logger.writeMessage("Average preference_score is:  " + Student.avgPreferenceScore, DebugLevel.CONTENTSATEACHENTRY);
		
	}
	
	/**
	 * This methods writes output to a file
	 * @param outputFileName
	 */
	public void writeSchedulesToFile(String outputFileName) {
		// TODO Auto-generated method stub
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			for (Student s : allocationStore) {

				writer.write(s.getName() + " " + s.getAllocatedCourseFirst() + " " + s.getAllocatedCourseSecond() + " "
						+ s.getAllocatedCourseThird()+ " " + s.getAllocatedCourseFourth()+ " " +
								s.getAllocatedCourseFifth()+ " " + s.getTotalPrefScore() + "\n");
				
			}
			writer.write("\nAverage preference_score is: " + Student.avgPreferenceScore + "\n\n");
			Logger.writeMessage("Average preference_score is:  " + Student.avgPreferenceScore, DebugLevel.NOOUTPUT);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception occured in writeSchedulesToFile method.");
			e.printStackTrace();
			System.exit(1);
		}

	}
}
