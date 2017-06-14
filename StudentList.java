

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * StudentList class contains methods for loading, saving, displaying
 * and sorting the student list
 *	
 */

 public class StudentList {
	
	 public static ArrayList<Student> studentList = new ArrayList<Student>();

	/**
	 * loadStudenList() Method will load all students from the defined constant
	 *                  STUDENT_FILE_PATH with the location of the Student.txt file.
	 *
	 * @param   None			With no params it will load Student.txt in local directory.
	 * @throws  IOException		Will throw IOException if file cannot be found or read.
	 * @returns void
	 */
	public static void loadStudentList() {

		String lineRead = null;     // String to hold each line read from the file.

		try {
			BufferedReader studentFile = null;
			studentFile = new BufferedReader(new FileReader(Constants.STUDENT_FILE_PATH));
			while ((lineRead = studentFile.readLine()) != null) {
				System.out.println(lineRead);
				String [] textArray = lineRead.split(",");
				Student obj = new Student(Integer.parseInt(textArray[0]),
								 textArray[1].trim(),textArray[2].trim(),
								 textArray[3].trim(),textArray[4].trim(),
								 textArray[5].trim(),textArray[6].trim(),
								 textArray[7].trim());
				studentList.add(obj);
			}
			studentFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * loadStudenList(String fName) Method will load all students from the file name passed.
	 *                              An overriding method to loadStudentList() with no parms.
	 * @param   fName			String type, allowing to specify the path and file to use.
	 * @throws  IOException		Will throw IOException if file cannot be found or read.
	 * @returns void
	 */
	public static void loadStudentList(String fName) {

		String lineRead = null;     // String to hold each line read from the file.

		try {
			BufferedReader studentFile = null;
			studentFile = new BufferedReader(new FileReader(fName));
			while ((lineRead = studentFile.readLine()) != null) {
				System.out.println(lineRead);
				String [] textArray = lineRead.split(",");
				Student studentObj = new Student(Integer.parseInt(textArray[0]),
								 		textArray[1].trim(),textArray[2].trim(),
								 		textArray[3].trim(),textArray[4].trim(),
								 		textArray[5].trim(),textArray[6].trim(),
								 		textArray[7].trim());
				studentList.add(studentObj);
			}
			studentFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * saveStudentList()	Method will save all students info to file Students.txt.
	 *                      Note: If data already exists this will overwrite after
	 *                            making a backup copy with appended date-time.
	 * 
	 *  @param	 None		With no parameters it will save to Student.txt file in
	 *                      local directory.
	 *  @throws  IOException
	 *  @returns void
	 */
	public static void saveStudentList() {

		File testFile, reNamedFile;
		File studentsFile;
		PrintWriter outFile;
		String fileName = Constants.STUDENT_FILE_PATH;
		
		try {
			testFile = new File(Constants.STUDENT_FILE_PATH);  
			
			// We need to test if file already exists, we want to save a backup
			// so we rename it with a the date & time appended.
			if (testFile.exists()) {
				reNamedFile = new File(AppendDate(fileName));
				testFile.renameTo(reNamedFile); // Rename to new file name with
												// date_time appended.
			}
			// Create a new Student.txt file (empty at this point)
			studentsFile = new File(fileName);
			outFile = new PrintWriter(studentsFile);
			if(studentList.size() != 0) {
				// Sort by LastName, FirstName
				Collections.sort(studentList);
				// Construct output record and write to file
				for(Student a: studentList){
					String studentRecord =  Integer.toString(a.getStudentID()) + "," 
											+ a.getFirstName().trim() + ","
											+ a.getLastName().trim() + "," 
											+ a.getAddress().trim() + ","
											+ a.getCity().trim() + "," 
											+ a.getState().trim() + "," 
											+ a.getZip().trim() + "," 
											+ a.getPassword().trim();
					outFile.println(studentRecord);
				}
				outFile.close();
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	
	public static String AppendDate(String fName) {
		
		SimpleDateFormat simpleFormatter = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date now = new Date();
		
		fName = fName + "_" + simpleFormatter.format(now);
		System.out.println(fName);
		return fName;
		}
	
	
}


