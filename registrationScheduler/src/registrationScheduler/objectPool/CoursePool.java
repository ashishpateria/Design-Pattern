package registrationScheduler.objectPool;

import java.util.Hashtable;
import java.util.Map;

import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * Coursepool Class 
 * @author ashishpateria
 *
 */
public class CoursePool extends ObjectPool<String> {

	private Map<String, Integer> courseObjects;

	private volatile static CoursePool uniqueInstance;

	/**
	 * Constructor sets capacity of each course to 60.
	 */
	public CoursePool() {
		super();
		courseObjects = new Hashtable<String, Integer>();
		courseObjects.put("A", 60);
		courseObjects.put("B", 60);
		courseObjects.put("C", 60);
		courseObjects.put("D", 60);
		courseObjects.put("E", 60); 
		courseObjects.put("F", 60);
		courseObjects.put("G", 60);
		courseObjects.put("H", 60);
		Logger.writeMessage("CoursePool Constructor called.", DebugLevel.CONSTRUCTOR);
	}
	
	//unique instance
	public static CoursePool getInstance() {
		if (uniqueInstance == null) {
			synchronized (CoursePool.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new CoursePool();
				}
			}
		}
		return uniqueInstance;
	}

	/**
	 * Function validates if given course is available in course objects pool.
	 * @param String COurse name
	 */
	@Override
	public boolean validate(String course) {
		// TODO Auto-generated method stub
		if (courseObjects.containsKey(course))
			return true;
		else
			return false;
	}

	/**
	 * Check for availability of seats.
	 * @param String course name
	 */
	@Override
	public boolean checkCourseSeats(String t) {
		// TODO Auto-generated method stub
		//System.out.println(courseObjects.get(t));
		if ((courseObjects.get(t)) > 0) {
			return true;
		} else
			return false;
	}

	/**
	 * updates course occupancy; if the course is alloted to any student,this method reduces its capacity by 1.
	 * @param String course name
	 */
	@Override
	public void updateCourseOccupancy(String t) {
		// TODO Auto-generated method stub
		if (t != null) {
			int value = courseObjects.get(t);
			value = value-1;
			if (value >= 0) {
				courseObjects.put(t, value);
			}
		}
	}
	
	public void getcoursetable(){
		System.out.println(courseObjects);
	}
	
	/**
	 * Coursepool class toString
	 */
	@Override
	public String toString() {
		return "\nOverriding toString in CoursePool Class";
	}
	
}

