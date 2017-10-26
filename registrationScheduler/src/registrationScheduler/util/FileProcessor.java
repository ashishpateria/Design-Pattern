package registrationScheduler.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import registrationScheduler.util.Logger.DebugLevel;
/**
 * This class has has methods to read from file and write to file.
 * @author ashishpateria
 *
 */
public class FileProcessor {

	private BufferedReader reader1;
	private BufferedReader reader2;
	private BufferedWriter writer;
	private String outputFile;
	
	/**
	 * constructor creates objects of BufferedReader and BufferedWriter classes.
	 * @param inputFileName1
	 * @param inputFileName2
	 * @param outputFileName
	 */
	public FileProcessor(String inputFileName1,String inputFileName2, String outputFileName) {
		
		Logger.writeMessage("FileProcessor Constructor called.", DebugLevel.CONSTRUCTOR);
		outputFile=outputFileName;
		try {
			//File validation whether files are empty
			File f1=new File(inputFileName1);
			if(f1.length()==0){
				System.err.println("File1 is empty");
				 System.exit(1);
			}
			File f2=new File(inputFileName2);
			if(f2.length()==0){
				System.err.println("File1 is empty");
				 System.exit(1);
			}
			this.reader1 = new BufferedReader(new FileReader(inputFileName1));
			this.reader2 = new BufferedReader(new FileReader(inputFileName2));
			this.writer = new BufferedWriter(new FileWriter(outputFileName));
		}catch(FileNotFoundException e){
			System.err.println("Please provide proper input file name.");
			e.printStackTrace();
			System.exit(1);
		}
		 catch (IOException e) {
				System.err.println("I/O exception has occured While Writing to File ");
				e.printStackTrace();
				System.exit(1);
		}
		
	}
	/**
	 * reads a line from a file
	 * @return line from file
	 */
	public String readFromFile1() {
		
		String inputLineFromFile = null;
		try {
			inputLineFromFile = this.reader1.readLine(); // buffer reader use
		} catch (FileNotFoundException e) {
			System.err.println("Exception as file not found");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Exception due Stream Reader I/O operation ");
			System.exit(1);
		}
		return inputLineFromFile;
	}
	
	/**
	 * 
	 * @return reads lines from file  2
	 */
	public String readFromFile2() {
		
		String inputLineFromFile = null;
		try {
			inputLineFromFile = this.reader2.readLine(); // buffer reader use
		} catch (FileNotFoundException e) {
			System.err.println("Exception as file not found");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Exception due Stream Reader I/O operation ");
			System.exit(1);
		}
		return inputLineFromFile;
	}
	
	/**
	 * write a line to a file
	 * @param writeThisLine
	 */
	public void writeToFile(String writeThisLine) {
		
		try {

			this.writer.write(writeThisLine); 
			this.writer.flush();
		} catch (FileNotFoundException e) {
			
			System.err.println("Exception as file not found");
			System.exit(1);
		} catch (IOException e) {
			
			System.err.println("Exception due Stream Writer I/O operation ");
			System.exit(1);
		}

	}
	public String getOutputFile(){
		return outputFile;
	}
	
	@Override
	public String toString() {
		return "FileProcessor [reader1=" + reader1 + ", reader2=" + reader2 + ", writer=" + writer + ", outputFile="
				+ outputFile + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
