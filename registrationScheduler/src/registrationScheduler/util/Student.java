package registrationScheduler.util;

import registrationScheduler.util.Logger.DebugLevel;

/**
 * student class with all the information about students.
 * @author ashishpateria
 *
 */
public class Student {

	private String name;
	private char pref1;
	private char pref2;
	private char pref3;
	private char pref4;
	private char pref5;
	private char allocatedCourseFirst;
	private char allocatedCourseSecond;
	private char allocatedCourseThird;
	private char allocatedCourseFourth;
	private char allocatedCourseFifth;
	private char[] allocatedCourses;
	private int totalPrefScore;
	public int coursecount=0;
	public  static float avgPreferenceScore;
	
	/**
	 * 
	 */
	public Student() {
		allocatedCourses = new char[5];
		Logger.writeMessage("Student Constructor called.", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * getter and setter methods for all the data members.
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getPref1() {
		return pref1;
	}
	public void setPref1(char pref1) {
		this.pref1 = pref1;
	}
	public char getPref2() {
		return pref2;
	}
	public void setPref2(char pref2) {
		this.pref2 = pref2;
	}
	public char getPref3() {
		return pref3;
	}
	public void setPref3(char pref3) {
		this.pref3 = pref3;
	}
	public char getPref4() {
		return pref4;
	}
	public void setPref4(char pref4) {
		this.pref4 = pref4;
	}
	public char getPref5() {
		return pref5;
	}
	public void setPref5(char pref5) {
		this.pref5 = pref5;
	}
	public char getAllocatedCourseFirst() {
		return allocatedCourseFirst;
	}
	public void setAllocatedCourseFirst(char allocatedCourseFirst) {
		this.allocatedCourseFirst = allocatedCourseFirst;
		allocatedCourses[0] = allocatedCourseFirst;
	}
	public char getAllocatedCourseSecond() {
		return allocatedCourseSecond;
	}
	public void setAllocatedCourseSecond(char allocatedCourseSecond) {
		this.allocatedCourseSecond = allocatedCourseSecond;
		allocatedCourses[1] = allocatedCourseSecond;
		
	}
	public char getAllocatedCourseThird() {
		return allocatedCourseThird;
	}
	public void setAllocatedCourseThird(char allocatedCourseThird) {
		this.allocatedCourseThird = allocatedCourseThird;
		allocatedCourses[2] = allocatedCourseThird;
	}
	public char getAllocatedCourseFourth() {
		return allocatedCourseFourth;
	}
	public void setAllocatedCourseFourth(char allocatedCourseFourth) {
		this.allocatedCourseFourth = allocatedCourseFourth;
		allocatedCourses[3] = allocatedCourseFourth;
	}
	public char getAllocatedCourseFifth() {
		return allocatedCourseFifth;
	}
	public void setAllocatedCourseFifth(char allocatedCourseFifth) {
		this.allocatedCourseFifth = allocatedCourseFifth;
		allocatedCourses[4] = allocatedCourseFifth;
	}
	public int getTotalPrefScore() {
		return totalPrefScore;
	}
	public void setTotalPrefScore(int totalPrefScore) {
		this.totalPrefScore = totalPrefScore;
	}
	public static float getAvgPreferenceScore() {
		return avgPreferenceScore;
	}
	public static void setAvgPreferenceScore(float avgPreferenceScore) {
		Student.avgPreferenceScore = avgPreferenceScore;
	}
	public char[] allocatedCourses(){
		return allocatedCourses;
	}
	
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", pref1=" + pref1 + ", pref2=" + pref2 + ", pref3=" + pref3 + ", pref4="
				+ pref4 + ", pref5=" + pref5 + ", allocatedCourseFirst=" + allocatedCourseFirst
				+ ", allocatedCourseSecond=" + allocatedCourseSecond + ", allocatedCourseThird=" + allocatedCourseThird
				+ ", allocatedCourseFourth=" + allocatedCourseFourth + ", allocatedCourseFifth=" + allocatedCourseFifth
				+ ", totalPrefScore=" + totalPrefScore + "]";
	}
		

}
