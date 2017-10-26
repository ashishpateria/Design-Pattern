package registrationScheduler.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import registrationScheduler.objectPool.CoursePool;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * Schedular class implements
 * 
 * @author ashishpateria
 *
 */

public class Scheduler implements ISchedular {

	CoursePool pool = CoursePool.getInstance();
	String obj = null;

	public Scheduler() {
		Logger.writeMessage("Scheduler Constructor called.", DebugLevel.CONSTRUCTOR);
	}

	/**
	 * function for scheduling algorithm
	 * 
	 * @param Student
	 *            object
	 * @return Student
	 */
	@Override
	public synchronized Student courseAllocation(Student s) {

		int prefScore;
		allocateUsinPreference(s, s.getPref1(), 1);
		allocateUsinPreference(s, s.getPref2(), 2);
		allocateUsinPreference(s, s.getPref3(), 3);
		allocateUsinPreference(s, s.getPref4(), 4);
		allocateUsinPreference(s, s.getPref5(), 5);
		char[] courses = s.allocatedCourses();
		Map<Character, Boolean> hmap = new LinkedHashMap<>();

		for (int i = 65; i < 73; i++)
			hmap.put((char) i, false);

		for (char c : courses)
			if (hmap.containsKey(c))
				hmap.put(c, true);
System.out.println();
		for (int i = 0; i < courses.length; i++) {
			if (courses[i] == '\u0000') {
				for (Map.Entry<Character, Boolean> entry : hmap.entrySet()) {
					if (!entry.getValue() && pool.checkAvailability(String.valueOf(entry.getKey()))) {
						obj = pool.acquireObject(String.valueOf(String.valueOf(entry.getKey())));
						if (obj != null) {
							if (i == 0)
								s.setAllocatedCourseFirst((entry.getKey()));
							if (i == 1)
								s.setAllocatedCourseSecond((entry.getKey()));
							if (i == 2)
								s.setAllocatedCourseThird((entry.getKey()));
							if (i == 3)
								s.setAllocatedCourseFourth((entry.getKey()));
							if (i == 4)
								s.setAllocatedCourseFifth((entry.getKey()));
							prefScore = s.getTotalPrefScore();
							prefScore += 0;
							s.coursecount++;
							hmap.put(entry.getKey(), true);
							s.setTotalPrefScore(prefScore);
							pool.releaseObject(obj);
							break;
						}

					}
				}
			}

		}

		return s;
	}

	// if(c == '\u0000')
	// System.out.println("True");

	// Set<Character> courses = new HashSet<>();
	// for(inti=0;i<5;i++)
	// if(s.getAllocatedCourseFifth() != null)
	// courses.add(s.getAllocatedCourseFifth())
	// else {
	//
	// }
	// courses[0] = s.getAllocatedCourseFirst() != '\u0000'?
	// s.getAllocatedCourseFirst() : '2';
	// courses[1] = s.getAllocatedCourseSecond() != '\u0000'?
	// s.getAllocatedCourseSecond() : '2';
	// courses[2] = s.getAllocatedCourseThird();
	// courses[3] = s.getAllocatedCourseFourth();
	// courses[4] = s.getAllocatedCourseFifth();
	// char[] actualCourses = {'A','B','C','D','E','F','G','H'};

	// Arrays.sort(courses);

	// pool.getcoursetable();
	// }

	public synchronized void allocateUsinPreference(Student s, char curr, int prefereceNumber) {
		int prefScore = 0;

		if (prefereceNumber == 1) {
			if (s.getPref1() == curr && pool.checkAvailability(String.valueOf(curr))) {
				obj = pool.acquireObject(String.valueOf(curr));
				if (obj != null) {
					s.setAllocatedCourseFirst(curr);
					prefScore = s.getTotalPrefScore();
					prefScore += 6;
					s.coursecount++;
					s.setTotalPrefScore(prefScore);
					pool.releaseObject(obj);

				}
			}
		}
		if (prefereceNumber == 2) {
			if (s.getPref2() == curr && pool.checkAvailability(String.valueOf(curr))) {
				obj = pool.acquireObject(String.valueOf(curr));
				if (obj != null) {
					s.setAllocatedCourseSecond(curr);
					prefScore = s.getTotalPrefScore();
					prefScore += 5;
					s.coursecount++;
					s.setTotalPrefScore(prefScore);
					pool.releaseObject(obj);

				}
			}
		}
		if (prefereceNumber == 3) {
			if (s.getPref3() == curr && pool.checkAvailability(String.valueOf(curr))) {
				obj = pool.acquireObject(String.valueOf(curr));
				if (obj != null) {
					s.setAllocatedCourseThird(curr);
					prefScore = s.getTotalPrefScore();
					prefScore += 4;
					s.coursecount++;
					s.setTotalPrefScore(prefScore);
					pool.releaseObject(obj);

				}
			}
		}
		if (prefereceNumber == 4) {
			if (s.getPref4() == curr && pool.checkAvailability(String.valueOf(curr))) {
				obj = pool.acquireObject(String.valueOf(curr));
				if (obj != null) {
					s.setAllocatedCourseFourth(curr);
					prefScore = s.getTotalPrefScore();
					prefScore += 3;
					s.coursecount++;
					s.setTotalPrefScore(prefScore);
					pool.releaseObject(obj);

				}
			}
		}
		if (prefereceNumber == 5) {
			if (s.getPref5() == curr && pool.checkAvailability(String.valueOf(curr))) {
				obj = pool.acquireObject(String.valueOf(curr));
				if (obj != null) {
					s.setAllocatedCourseFifth(curr);
					prefScore = s.getTotalPrefScore();
					prefScore += 2;
					s.coursecount++;
					s.setTotalPrefScore(prefScore);
					pool.releaseObject(obj);

				}
			}
		}

	}

	/**
	 * calculate average preference score for all the students
	 */

	@Override
	public void avgPreferenceScore(List<Student> list) {
		if (list != null) {
			try {
				int totalScore = 0;
				synchronized (list) {
					for (Student s : list) {
						totalScore = totalScore + s.getTotalPrefScore();

					}
					Student.avgPreferenceScore = (float) totalScore / 80;
				}
			}

			catch (Exception e) {
				System.err.println("I/O exception has occured in averagePreferenceScore method");
				e.printStackTrace();
				System.exit(1);
			}
		}

	}

}
