

/**
 * Course class contains information about each of the
 * course objects.
 *
 */

public class Course implements Comparable<Course>{

	 private String courseID;
	 private String startDate;
	 private String endDate;
	 private String courseName;
	 private String courseDescription;
	 private int courseLimit;
	 private int studentsEnrolled;

	 /**
	  * Course Constructor
	  */
	 public Course(String courseID, String startDate, String endDate,String courseName,
	    String courseDescription, int courseLimit, int studentsEnrolled)
	    {
			this.courseID = courseID;
			this.startDate = startDate;
			this.endDate = endDate;
			this.courseName = courseName;
			this.courseDescription = courseDescription;
			this.courseLimit = courseLimit;
			this.studentsEnrolled = studentsEnrolled;
		}

	/**
	 * Getter & Setter Methods
	 */

	 protected void setCourseID(String courseID) {
		 this.courseID = courseID;
	 }

	 protected void setStartDate(String startDate) {
		 this.startDate = startDate;
	 }

	 protected void setEndDate(String endDate) {
		 this.endDate = endDate;
	 }

	 protected void setCourseName(String courseName) {
		 this.courseName = courseName;
	 }

	 protected void setCourseDescription(String courseDescription) {
		 this.courseDescription = courseDescription;
	 }

	 protected void setCourseLimit(int courseLimit) {
		 this.courseLimit = courseLimit;
	 }

	 protected void setStudentsEnrolled(int studentsEnrolled) {
		 this.studentsEnrolled = studentsEnrolled;
	 }

	 protected String getCourseID() {
		 return courseID;
	 }

	 protected String getCourseName() {
		 return courseName;
	 }

	 protected String getStartDate() {
		 return startDate;
	 }

	 protected String getEndDate() {
		 return endDate;
	 }

	 protected String getCourseDescription() {
		 return courseDescription;
	 }

	 protected int getCourseLimit() {
		 return courseLimit;
	 }

	 protected int getStudentsEnrolled() {
		 return studentsEnrolled;
	 }

	/**
	 * isCourseFilled() Method will determine if a course is filled
	 *
	 * @param <getCourseLimit> get the course limit for the course
	 * @param <getStudentsEnrolled> get the current number of students enrolled in course
	 * @throws
	 * @return a boolean: True if course is filled / False if course still has availability
	 */

	 protected boolean isCourseFilled(int getCourseLimit, int getStudentsEnrolled) {
		 boolean result;
		 if (getStudentsEnrolled == getCourseLimit)
		 	result = true;
		 else
		 	result = false;
		 return result;
	 }

	/**
	 * compareTo() Method to compare two courses to each other by course ID
	 *
	 * @param <Course> get the course course ID for the Course object
	 * @throws
	 * @return Course ID
	 */
	 public int compareTo(Course course) {
	 		return this.courseName.compareTo(course.getCourseName());
	}

	/**
	 * toStringCourse() Method formats the course listing
	 *
	 * @param <getCourseID> get the course ID for the Course object
	 * @param <getStartDate> get the start date for the Course object
	 * @param <getEndDate> get the end date for the Course object
	 * @param <getCourseName> get the course name for the Course object
	 * @param <getCourseDescription> get the course description for the Course object
	 * @param <getCourseLimit> get the course limit for the Course object
	 * @param <getStudentsEnrolled> get the number of students enrolled for the Course object
	 * @throws
	 * @return Course catalog
	 */

	 protected String toStringCourse() {
		 return("\nCourse ID: " + getCourseID() + "\n"
				+ "Course Start Date: " + getStartDate() + "\n"
				+ "Course End Date: " + getEndDate() + "\n"
				+ "Course Name: " + getCourseName() + "\n"
				+ "Course Description: " + getCourseDescription() + "\n"
				+ "Course Limit: " + getCourseLimit() + "\n"
				+ "Number of Students Enrolled: " + getStudentsEnrolled());
	 }
 }
