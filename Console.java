

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The abstract Console class allows the subclass to show list of options and get input from screen.
 * It provides abstract methods to implement the list of options and show the console based on selected option.
 */
public abstract class Console {

	private static Scanner inputScanner;
	
	/**
	 * Default constructor
	 */
	public Console() {
		// initialize the scanner
		inputScanner = new Scanner(System.in);
	}
	
	/**
	 * Get the scanner
	 * @return the inputScanner
	 */
	public static Scanner getInputScanner() {
		return inputScanner;
	}
	
	/**
	 * Close the scanner
	 */
	public static void closeInputScanner() {
		if (inputScanner != null)
			inputScanner.close();
	}
	
	/**
	 * Display the console with/without list of options.
	 * @param hasOptionList
	 */
	public void show(boolean hasOptionList) {
		if (hasOptionList) {
			System.out.println();
			showOptionList();
			getOptionInput();
		}
	}
	
	/**
	 * Get the user input for the option
	 */
	private void getOptionInput() {
		try {
			// check if input is in valid format and parse it
			parseOptionInput();
		} catch (InputMismatchException e) {
			System.out.println(this.getClass().getName() + ": Error! " + e.getMessage());
		}
	}
	
	/**
	 * Check to see if the input is a valid integer
	 */
	private void parseOptionInput() {
		System.out.print(Constants.SELECT_OPTION);
		
		int option = 0;
		try {
			option = Integer.parseInt(inputScanner.nextLine());
			selectOption(option);
		}
		catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_FORMAT);
			getOptionInput();
		}
	}
	
	/**
	 * Implement to show list of options
	 */
	abstract void showOptionList();
	
	/**
	 * Implement to show console based on selected option
	 */
	abstract void selectOption(int option);
}