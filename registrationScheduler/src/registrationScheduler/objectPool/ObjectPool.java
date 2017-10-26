package registrationScheduler.objectPool;

import java.util.Hashtable;
import java.util.Map;

import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

/**
 * 
 * @author ashishpateria
 * Abstract class Object Pool
 * @param <T> String Course name
 */
public abstract class ObjectPool<T> {
	
	public abstract boolean validate(T o);
	
	public abstract boolean checkCourseSeats(T t);
	
	public abstract void updateCourseOccupancy(T t);
	
	private Map<String, Integer> locked, unlocked;
	/**
	 * Creating 2 hashmaps locked and unlocked and putting courses initially in unlocked hashmap with course capacity set to 60.
	 */
	public ObjectPool() {
		Logger.writeMessage("ObjectPool Constructor called.", DebugLevel.CONSTRUCTOR);
		locked = new Hashtable<String, Integer>();
		unlocked = new Hashtable<String, Integer>();
		unlocked.put("A", 60);
		unlocked.put("B", 60);
		unlocked.put("C", 60);
		unlocked.put("D", 60);
		unlocked.put("E", 60);
		unlocked.put("F", 60); 
		unlocked.put("G", 60);
		unlocked.put("H", 60);
	}

	/**
	 * synchronized method checks if course t is available i.e. it is in unlocked state and it has more than 0 seats.
	 * @param t String course name
	 * @return
	 */
	public synchronized boolean checkAvailability(T t){
		
		if (!checkCourseSeats(t))
			return false;
		if (checkCourseSeats(t) && unlocked.size() >= 0) {
			if (unlocked.containsKey(t)) {
				if (unlocked.get(t) >= 0)
					return true;
				else
					return false;
			} else
				return false;
			}
		return false;
	}
	
	/**
	 * synchronized method which acquires object i.e puts lock on course t
	 * @param t
	 * @return
	 */
	public synchronized T acquireObject(T t) {
		try {
				if (unlocked.size() >= 0) {
				if (unlocked.containsKey(t) && (unlocked.get(t) > 0) && validate(t)) {
					int value = unlocked.get(t);
					unlocked.remove(t);
					locked.put(t.toString(), value);
					return t;
				} else if (unlocked.containsKey(t) && (unlocked.get(t) == 0) && validate(t)) {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			t = null; // Need to check if this returns -- it didn't acquire
						// object and failed to acquire it.
		}
		return t;
	}
	
	/**
	 * synchronized method to release object i.e putting course t in unlocked state.
	 * @param t
	 */
	public synchronized void releaseObject(T t) {
		synchronized (t) {
			if (locked.containsKey(t)) {
				int value = locked.get(t);
				value = value - 1;
				locked.remove(t);
				unlocked.put(t.toString(), value);
				updateCourseOccupancy(t);
			}
		}
	
	}
	
}
