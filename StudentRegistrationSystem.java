
/*
 * The StudentRegistrationSystem class runs the main thread and allows the user to navigate the application.
 */
public class StudentRegistrationSystem extends Console {

	private Login login;
	private Registrar registrar;
	private CourseCatalog courseCatalog;
	private MyCourseSchedule myCourseSchedule;
	private NewStudentAccount newStudentAccount;

	private static StudentRegistrationSystem srs;

	/**
	 * Default constructor
	 */
	public StudentRegistrationSystem() {
		login = new Login();
		registrar = new Registrar();
		courseCatalog = new CourseCatalog();
    	myCourseSchedule = new MyCourseSchedule();
		newStudentAccount = new NewStudentAccount();
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		getSingleInstance().show();	
	}
	
	/**
	 * Get the login instance
	 * @return the login
	 */
	public static Login getLogin() {
		return getSingleInstance().login;
	}

	/**
	 * Get the registrar instance
	 * @return the registrar
	 */
	public static Registrar getRegistrar() {
		return getSingleInstance().registrar;
	}
	
	/**
	 * Get the courseCatalog instance
	 * @return the courseCatalog
	 */
	public static CourseCatalog getCourseCatalog() {
		return getSingleInstance().courseCatalog;
	}
	
	/**
	 * Get the myCourseSchedule instance
	 * @return the myCourseSchedule
	 */
	public static MyCourseSchedule getMyCourseSchedule() {
		return getSingleInstance().myCourseSchedule;
	}
	
	/**
	 * Get the newStudentAccount instance
	 * @return the newStudentAccount
	 */
	public static NewStudentAccount getNewStudentAccount() {
		return getSingleInstance().newStudentAccount;
	}
	
	/**
	 * Shows the SRS console with title
	 */
	public void show() {
		System.out.println();
		System.out.println(Constants.STARS + Constants.OPTION_STUDENT_ACCOUNT_LOGIN + Constants.STARS);
		super.show(true);
	}
	
	/* (non-Javadoc)
	 * @see x464010.teamb.srs.Console#showOptionList()
	 */
	@Override
	protected void showOptionList() {
		Student student = getLogin().getStudent();
		
		if (student != null && getLogin().isLoggedIn()) {
			StringBuffer welcomeMessage = new StringBuffer(Constants.WELCOME);
	    	welcomeMessage.append(student.getFirstName()).append(" ").append(student.getLastName()).append("!");
	    	
	    	System.out.println(welcomeMessage.toString());
	    	System.out.println();
			System.out.println("1. " + Constants.OPTION_COURSE_CATALOG);
			System.out.println("2. " + Constants.OPTION_MY_COURSE_SCHEDULE);
			System.out.println("3. " + Constants.OPTION_LOGOUT);
		}
		else {
			System.out.println("1. " + Constants.OPTION_COURSE_CATALOG);
			System.out.println("2. " + Constants.OPTION_STUDENT_ACCOUNT_LOGIN);
			System.out.println("3. " + Constants.OPTION_NEW_STUDENT_ACCOUNT);
		}
		System.out.println("4. " + Constants.OPTION_QUIT);
	}
	
	/* (non-Javadoc)
	 * @see x464010.teamb.srs.Console#selectOption(int)
	 */
	@Override
	protected void selectOption(int option) {
		if (getLogin().isLoggedIn()) {
			switch (option) {
				case Constants.COURSE_CATALOG:
					getCourseCatalog().show();
					break;
				case Constants.MY_COURSE_SCHEDULE:
					getMyCourseSchedule().show();
					break;
				case Constants.LOGOUT:
					getLogin().setLoggedIn(false);
					getLogin().setStudent(null);
					System.out.println();
					System.out.println(Constants.LOGOUT_SUCCESS);
					show(true);
					break;
				case Constants.QUIT:
					quit();
				default:
					System.out.println(Constants.INVALID_OPTION);
					getSingleInstance().show(true);
					break;
			}
		}
		else {
			switch (option) {
				case Constants.COURSE_CATALOG:
					getCourseCatalog().show();
					break;
				case Constants.STUDENT_ACCOUNT_LOGIN:
					getLogin().show();
					break;
				case Constants.NEW_STUDENT_ACCOUNT:
					getNewStudentAccount().show();
					break;
				case Constants.QUIT:
					quit();
				default:
					System.out.println(Constants.INVALID_OPTION);
					getSingleInstance().show(true);
					break;
			}
		}
	}
	
	/**
	 * Get the single instance of the application
	 */
	protected static StudentRegistrationSystem getSingleInstance() {
        if (srs == null)
            srs = new StudentRegistrationSystem();
        return srs;
    }
	
	/**
	 * Quit the application
	 */
	private void quit() {
		super.closeInputScanner();
		System.exit(0);
	}
}