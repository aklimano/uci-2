

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;
import java.util.*;

/**
 * The NewStudentAccount class allows the student to create a new account with auto-generated Student ID.
 * 
 */
public class NewStudentAccount extends Console {
	
	private Student newStudent = null;
	
	/**
	 * Shows the console with title and prompts to enter new student account information
	 */
	public void show() {
		System.out.println();
		System.out.println(Constants.STARS + Constants.OPTION_NEW_STUDENT_ACCOUNT + Constants.STARS);
		show(true);
	}
	
	/**
 	 * Shows the console and prompts to enter new student account information
 	 */
	@Override
	public void show(boolean hasOptionList) {
		/**
	 	 * Get input from user for new Student data and
	 	 * write to student.txt as a new line (appended to existing data)
	 	 */
		try {
			Scanner inputScanner = Console.getInputScanner();
			String firstName = null, lastName = null, streetAddress = null, city = null, state = null, zip = null, password = null;
			
			System.out.println();
			
			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.FIRST_NAME);
			while((firstName = inputScanner.nextLine()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.FIRST_NAME);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.LAST_NAME);
			while((lastName = inputScanner.nextLine()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.LAST_NAME);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STREET_ADDRESS);
			while((streetAddress = inputScanner.nextLine()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STREET_ADDRESS);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.CITY);
			while((city = inputScanner.nextLine()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.CITY);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STATE);
			while((state = inputScanner.nextLine()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STATE);
			};

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.ZIP);
			while((zip = inputScanner.nextLine()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.ZIP);
			};

			System.out.print(Constants.CREATE_PASSWORD);
			while((password = inputScanner.nextLine()).isEmpty()){
				System.out.println("Sorry, cannot be blank.");
				System.out.print(Constants.CREATE_PASSWORD);
			};
			
			newStudent = new Student(generateStudentId(), firstName.trim(), lastName.trim(), streetAddress.trim(), city.trim(), state.trim(), zip.trim(), password.trim());
			
			saveStudentInfo();
		} catch (InputMismatchException e) {
			System.out.println(this.getClass().getName() + ": Error! " + e.getMessage());
		}
	}
	
	@Override
	protected void showOptionList() {
		
	}
	
	@Override
	protected void selectOption(int option) {
		
	}
	
	/**
 	 * Generates the Student ID for new account
 	 */
	private int generateStudentId() {
		/**
	 	 * Auto-generate unique student ID
	 	 * Read in existing student IDs from Student.txt
	 	 * Save studentID data to studentList ArrayList
	 	 * Sort studentList
	 	 * Maximum studentID is the last value in sorted studentList
	 	 * Create new student ID by adding 1 to previous max studentID
	 	 */
		ArrayList<Integer> studentIdList = new ArrayList<Integer>();
		
		try {
			File existingStudentFile = new File(Constants.STUDENT_FILE_PATH);
			FileReader existingStudentReader = new FileReader(existingStudentFile);
			BufferedReader existingStudentBuffReader = new BufferedReader(existingStudentReader);

			String line = null;

			while ((line = existingStudentBuffReader.readLine()) != null) {
				String studentAttributes[] = line.split(",");
				studentIdList.add(Integer.parseInt(studentAttributes[0]));
			}

			existingStudentBuffReader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Collections.sort(studentIdList);
		int maxStudentID = studentIdList.get(studentIdList.size() - 1);
		int newStudentID = maxStudentID + 1;
		
		return newStudentID;
	}
	
	/**
	 * Saves the new student account info to the file
	 */
	private void saveStudentInfo() {
		BufferedWriter buffWriter = null;
		try {
			// Open file with append flag set to true will cause string to be appended to file
			buffWriter = new BufferedWriter(new FileWriter(Constants.STUDENT_FILE_PATH,true));
			buffWriter.newLine();
			buffWriter.write(newStudent.toString());
			buffWriter.close();
			
			System.out.println();
			System.out.println(Constants.NEW_STUDENT_ACCOUNT_SUCCESS + newStudent.getStudentID() + ". " + Constants.SAVE_STUDENT_ID);
			
			StudentRegistrationSystem.getLogin().validateInput(newStudent.getStudentID(), newStudent.getPassword(), Constants.SRS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}