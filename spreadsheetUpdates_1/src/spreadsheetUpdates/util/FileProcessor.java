package spreadsheetUpdates.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import spreadsheetUpdates.util.Logger.DebugLevel;

public class FileProcessor {
	private BufferedReader reader;
	private BufferedWriter writer;
	private String outputFile;
	public FileProcessor(String inputFile,String outputFile) {
		Logger.writeMessage("FileProcessors Contructor called ", DebugLevel.CONSTRUCTOR);
		File f1=new File(inputFile);
		if(f1.length()==0){
			System.err.println("InputFile is empty");
			 System.exit(1);
		}
		
		try {
				this.reader=new BufferedReader(new FileReader(inputFile));
				this.writer=new BufferedWriter(new FileWriter(outputFile));
			
		} catch (FileNotFoundException e) {
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
	
	public String readFromFile(){
		String inputFromFile=null;
		try {
			inputFromFile=this.reader.readLine();
		} catch (FileNotFoundException e) {
			System.err.println("Exception as file not found");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Exception due Stream Reader I/O operation ");
			e.printStackTrace();
			System.exit(1);
		}
		
		return inputFromFile;
	}
	
	public void writeToFile(String writeThisLine){
		try {
			this.writer.write(writeThisLine);
			this.writer.flush();
		} catch (FileNotFoundException e) {
			System.err.println("Exception as file not found");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Exception due Stream Writer I/O operation ");
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	@Override
	public String toString() {
		return "FileProcessor [reader=" + reader + ", writer=" + writer + ", outputFile=" + outputFile
				+ ", readFromFile()=" + readFromFile() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

	
}
