

/**
 * Registration class holds registration info of courses
 * registered by students.
 *  
 * 
 */

import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.StringBuilder;

public class Registration implements Comparator <Registration>   {

	private int regNum;
	private int studentID;
	private String courseID;
	private String regDate;
	
	/**
	 * Constructor used for registering a course	
	 * 
	 * @param regNum		Unique registration key for tracking registrations.
	 * @param studentID		Students ID used to register for course.
	 * @param courseID		The Course ID associated with the class being registered.
	 */
	public Registration(int regNum, int studentID, String courseID)
	{
		this.regNum = regNum;
		this.studentID = studentID;
		this.courseID = courseID;
		this.regDate = getDate();
	}

	/**
	 * Constructor used when reading in registration info from file.
	 * 
	 * @param fileRecord	A string of array elements which have been 
	 * 						parsed from the comma separated text file.
	 */
	public Registration(String[] fileRecord)
	{
		regNum = Integer.parseInt(fileRecord[0]);
		studentID = Integer.parseInt(fileRecord[1]);
		courseID = fileRecord[2].trim();
		regDate = fileRecord[3].trim();
	}
	
	public Registration() {
		
	}
	
	/**
	 * getRegNum() method
	 * 
	 * @return	regNum		Unique Registration Key of registration transaction.
	 */
	protected int getRegNum() {
		return regNum;
	}
	
	/**
	 * setRegNum(int regNum)
	 * @param regNum		Used to set regNum when deleting a registration record
	 */
	protected void setRegNum(int regNum) {
		this.regNum = regNum;
	}

	/**
	 * getStudentID() method
	 * 
	 * @return	studentID	The studentID associated with this registration transaction.
	 */
	protected int getStudentID() {
		return studentID;
	}

	/**
	 * getCourseID() method
	 * 
	 * @return	courseID	The courseID associated with this registration transaction.
	 */
	protected String getCourseID() {
		return courseID;
	}

	/**
	 * getRegDate() method
	 * 
	 * @return	regDate		The date in String format associated with this transaction.
	 * 						The date of the transaction is important as it is used to
	 * 						compare to Starting and Ending Times of Courses.
	 */
	protected String getRegDate() {
		return regDate;
	}

	/**
	 * getDate() method
	 * 
	 * @return	String		Date formatted in "MM/dd/yyyy" style.
	 */
	private String getDate() {
		SimpleDateFormat simpleFormatter = new SimpleDateFormat("MM/dd/yyyy");
		Date now = new Date();
		return simpleFormatter.format(now);
	}
	
	/**
	 * toString() method
	 * 	
	 * @return	String		String returned showing variables and their values.
	 */
	public String toString()
	{
		return "Registration [regNum=" + regNum +", studentID=" + 
				studentID + ", courseID=" + courseID + 
				", regDate=" + regDate + "]";
	}
	
	protected String recordFormater()
	{
		StringBuilder outRecord = new StringBuilder();
		String line = regNum + "," + studentID + "," + courseID + "," 
					+ "\"" + regDate + "\"";
		outRecord.append(line);
		return outRecord.toString();
		
	}
	
	/**
	 * Overrides compare method allowing sorting of registration keys
	 * in ascending order.  Invoke by using Collections.sort(registrationList, new Registration () )
	 * 
	 */
	public int compare(Registration r1, Registration r2) {
		return(r1.regNum - r2.regNum);	// Will sort in increasing regNum value
	}
	
	
}
