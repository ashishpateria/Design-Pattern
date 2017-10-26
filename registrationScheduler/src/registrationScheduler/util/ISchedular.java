package registrationScheduler.util;

import java.util.List;

/**
 * Interface for scheduler class
 * @author ashishpateria
 *
 */
public interface ISchedular {
	
	Student courseAllocation(Student student);
	void avgPreferenceScore(List<Student> list);
}
