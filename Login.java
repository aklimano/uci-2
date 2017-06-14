

import java.io.*;
import java.util.Scanner;

/**
 * The Login class prompts the student to enter credentials, validate them and redirects to appropriate console.
 * 
 */
public class Login extends Console {
	
	private Student student = null;
	private boolean isLoggedIn = false;
	
	/**
	 * Gets the student
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Sets the student
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * Gets the isLoggedIn
	 * @return the isLoggedIn
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * Sets the isLoggedIn
	 * @param isLoggedIn the isLoggedIn to set
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	/**
	 * Shows the login console with title and prompts to enter Student ID and password
	 */
	public void show() {
		System.out.println();
		System.out.println(Constants.STARS + Constants.OPTION_STUDENT_ACCOUNT_LOGIN + Constants.STARS);
		show(Constants.SRS);
	}
	
	/**
	 * Shows the login console and prompts to enter Student ID and password
	 */
	public void show(int redirectConsoleId) {
		try {
			Scanner inputScanner = Console.getInputScanner();
			int enteredId;
			String enteredPassword;
			
			System.out.println();
			System.out.println(Constants.STUDENT_LOGIN);
			
			// check if student id is in valid format and parse it
			enteredId = parseStudentId(inputScanner);
			
			System.out.print(Constants.PASSWORD);
			enteredPassword = inputScanner.nextLine();
			
			validateInput(enteredId, enteredPassword, redirectConsoleId);
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + ": Error! " + e.getMessage());
		}
	}
	
	@Override
	protected void showOptionList() {
		
	}
	
	/** 
	 * Redirects to the desired option (console)
	 * @param option
	 */
	@Override
	protected void selectOption(int option) {
		switch (option) {
			case Constants.REGISTER_COURSE:
				StudentRegistrationSystem.getRegistrar().show(Registrar.REGISTER);
				break;
			case Constants.SRS:
			default:
				StudentRegistrationSystem.main(null);
				break;
		}
	}
	
	/**
	 * Checks to see if Student ID and password match the entry in the file
	 * @param id
	 * @param password
	 */
	protected void validateInput(int id, String password, int redirectConsoleId) {
		boolean isValid = false;
		File inputFile;
        Scanner fileScanner;
        String currentLine = "";
        String[] studentDetails = {};
        try {
        	inputFile = new File(Constants.STUDENT_FILE_PATH);
			fileScanner = new Scanner(inputFile);
		    
			while (fileScanner.hasNextLine()) {
      			currentLine = fileScanner.nextLine();
      			studentDetails = currentLine.split(",");

      			// check if input matches information from Student.txt file
    		    if(Integer.parseInt(studentDetails[0]) == id && studentDetails[7].equals(password)) {
    		    	isValid = isLoggedIn = true;
    		    	setStudent(new Student(	Integer.parseInt(studentDetails[0]),
    		    							studentDetails[1],
    		    							studentDetails[2],
    		    							studentDetails[3],
    		    							studentDetails[4],
    		    							studentDetails[5],
    		    							studentDetails[6],
    		    							studentDetails[7]));
    		    	break;
    		    }
			}
		    
		    fileScanner.close();
		    
		    if (isValid) {
		        selectOption(redirectConsoleId);
		    }
		    else {
		    	System.out.print(Constants.INVALID_LOGIN);
		    	show(redirectConsoleId);
		    }
		 }
	    catch(FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Check to see if the input is a valid integer
	 */
	private int parseStudentId(Scanner inputScanner) {
		System.out.print(Constants.STUDENT_ID);
		int id = 0;
		try {
			id = Integer.parseInt(inputScanner.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_FORMAT);
			id = parseStudentId(inputScanner);
		}
		return id;
	}
}