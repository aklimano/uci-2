

/**
 * The MyCourseSchedule class shows the list of courses student has enrolled in and gives the option to unregister a course.
 * 
 */
public class MyCourseSchedule extends Console {
	
	/**
	 * Shows my course schedule console with title and list of courses
	 */
	public void show() {
		int studentId = StudentRegistrationSystem.getLogin().getStudent().getStudentID();
		System.out.println();
		System.out.println(Constants.STARS + Constants.OPTION_MY_COURSE_SCHEDULE + Constants.STARS);
		StudentRegistrationSystem.getRegistrar().myCourseSchedule(studentId);
		super.show(true);
	}
	
	/* (non-Javadoc)
	 * @Console#showOptionList()
	 */
	@Override
	protected void showOptionList() {
		System.out.println("1." + Constants.OPTION_UNREGISTER_COURSE);
		System.out.println("2." + Constants.OPTION_BACK_SRS);
	}
	
	/* (non-Javadoc)
	 * @Console#selectOption(int)
	 */
	@Override
	protected void selectOption(int option) {
		switch (option) {
			case Constants.UNREGISTER_COURSE:
				StudentRegistrationSystem.getRegistrar().show(Registrar.UNREGISTER);
				break;
			case Constants.SRS:
				StudentRegistrationSystem.main(null);
				break;
			default:
				System.out.println(Constants.INVALID_OPTION);
				show(true);
				break;
		}
	}
}