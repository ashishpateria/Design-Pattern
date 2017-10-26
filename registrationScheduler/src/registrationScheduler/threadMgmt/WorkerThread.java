package registrationScheduler.threadMgmt;

import java.util.List;

import registrationScheduler.store.Results;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.ISchedular;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Student;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * Class implements run method of threads
 * 
 * @author ashishpateria
 *
 */
public class WorkerThread implements Runnable {

	private FileProcessor fileProcessor;
	private Results results;
	private ISchedular scheduler;

	/**
	 * 
	 * @param fileProcessor
	 * @param results
	 * @param scheduler
	 */
	public WorkerThread(FileProcessor fileProcessorIn, Results resultsIn, ISchedular schedulerIn) {
		fileProcessor = fileProcessorIn;
		results = resultsIn;
		scheduler = schedulerIn;
		Logger.writeMessage("Worker Thread Constructor called.", DebugLevel.CONSTRUCTOR);
	}

	/*
	 * The run method of the worker thread should do the following till the end
	 * of file is reached: Invoke a method in the FileProcessor to read one line
	 * as a string Run your scheduler algorithm to assign courses to this
	 * student. Store the results in the data structure in the Results instance
	 * Do add and drop operation of courses.
	 */
	public void run() {
		String inputLine = null;

		try {
			while ((inputLine = fileProcessor.readFromFile1()) != null) {
				String[] student = inputLine.replaceAll("\\s+", " ").split(" ");
				Student s = null;
				if (student != null && student.length == 6) {
					s = new Student();
					s.setName(student[0]);
					s.setPref1(student[1].charAt(0));
					s.setPref2(student[2].charAt(0));
					s.setPref3(student[3].charAt(0));
					s.setPref4(student[4].charAt(0));
					s.setPref5(student[5].charAt(0));
					Student studentAllocated = scheduler.courseAllocation(s);

					results.resultAllocationStore(studentAllocated);
					Logger.writeMessage("Thread is called. ", DebugLevel.RUN);
				}
			}
			//scheduler.avgPreferenceScore(results.allocationStore);
		} catch (NumberFormatException e) {
			System.err.println("Number format exception in readFromFile method.");
			e.printStackTrace();
			System.exit(1);
		}

		try {
			List<Student> list = results.allocationStore;
			while ((inputLine = fileProcessor.readFromFile2()) != null) {
				String[] adddrop = inputLine.replaceAll("\\s+", " ").split(" ");
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getName().equalsIgnoreCase(adddrop[0])) {

						if (adddrop[1].equalsIgnoreCase("0")) {
							for (int j = 2; j < adddrop.length; j++) {
								if (list.get(i).getAllocatedCourseFirst() == adddrop[j].charAt(0)) {
									list.get(i).setAllocatedCourseFirst('\u0000');
									list.get(i).coursecount--;
									continue;
								} else if (list.get(i).getAllocatedCourseSecond() == adddrop[j].charAt(0)) {
									list.get(i).setAllocatedCourseSecond('\u0000');
									list.get(i).coursecount--;
									continue;
								} else if (list.get(i).getAllocatedCourseThird() == adddrop[j].charAt(0)) {
									list.get(i).setAllocatedCourseThird('\u0000');
									list.get(i).coursecount--;
									continue;
								} else if (list.get(i).getAllocatedCourseFourth() == adddrop[j].charAt(0)) {
									list.get(i).setAllocatedCourseFourth('\u0000');
									list.get(i).coursecount--;
									continue;
								} else if (list.get(i).getAllocatedCourseFifth() == adddrop[j].charAt(0)) {
									list.get(i).setAllocatedCourseFifth('\u0000');
									list.get(i).coursecount--;
									continue;
								}

							}
						}

						if (adddrop[1].equalsIgnoreCase("1")) {
							for (int j = 2; j < adddrop.length; j++) {
								if (list.get(i).coursecount < 5) {
									if (list.get(i).getAllocatedCourseFirst() == '\u0000') {
										list.get(i).setAllocatedCourseFirst(adddrop[j].charAt(0));
										list.get(i).coursecount++;
										continue;
									}
									if (list.get(i).getAllocatedCourseSecond() == '\u0000') {
										list.get(i).setAllocatedCourseSecond(adddrop[j].charAt(0));
										list.get(i).coursecount++;
										continue;
									}
									if (list.get(i).getAllocatedCourseThird() == '\u0000') {
										list.get(i).setAllocatedCourseThird(adddrop[j].charAt(0));
										list.get(i).coursecount++;
										continue;
									}
									if (list.get(i).getAllocatedCourseFourth() == '\u0000') {
										list.get(i).setAllocatedCourseFourth(adddrop[j].charAt(0));
										list.get(i).coursecount++;
										continue;
									}
									if (list.get(i).getAllocatedCourseFifth() == '\u0000') {
										list.get(i).setAllocatedCourseFifth(adddrop[j].charAt(0));
										list.get(i).coursecount++;
										continue;
									}
								}
							}
						}
					}
				}

			}
			results.allocationStore = list;
			//scheduler.avgPreferenceScore(results.allocationStore);
		} catch (NumberFormatException e) {
			System.err.println("Number format exception in readFromFile method.");
			e.printStackTrace();
			System.exit(1);
		}
		List<Student> list1 = results.allocationStore;
		for (int i = 0; i < list1.size(); i++) {
			list1.get(i).setTotalPrefScore(0);
			int prefscore = 0;
			if (list1.get(i).getPref1() == list1.get(i).getAllocatedCourseFirst()) {
				prefscore += 6;
			}
			if (list1.get(i).getPref2() == list1.get(i).getAllocatedCourseSecond()) {
				prefscore += 5;
			}
			if (list1.get(i).getPref3() == list1.get(i).getAllocatedCourseThird()) {
				prefscore += 4;
			}
			if (list1.get(i).getPref4() == list1.get(i).getAllocatedCourseFourth()) {
				prefscore += 3;
			}
			if (list1.get(i).getPref5() == list1.get(i).getAllocatedCourseFifth()) {
				prefscore += 2;
			}
			list1.get(i).setTotalPrefScore(prefscore);
		}
		scheduler.avgPreferenceScore(results.allocationStore);

	}

	@Override
	public String toString() {
		return "WorkerThread [fileProcessor=" + fileProcessor + ", results=" + results + ", scheduler=" + scheduler
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
