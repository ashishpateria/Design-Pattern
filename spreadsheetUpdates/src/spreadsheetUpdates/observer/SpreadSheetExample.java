package spreadsheetUpdates.observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.Logger.DebugLevel;
/**
 * Spreadsheet Class which do all the evaluation of the spreadsheet
 * @author ashishpateria
 *
 */
public class SpreadSheetExample {

	private FileProcessor  fileProcessor;
	private ArrayList<Cell> cellList = new ArrayList<>();
	private static BufferedWriter writer;
	
	public SpreadSheetExample(FileProcessor fileProcessorIn) {
		Logger.writeMessage("SpreadSheetExample called ", DebugLevel.CONSTRUCTOR);
		fileProcessor=fileProcessorIn;
		
	}

	/**
	 * Function to check string is number
	 * @param String
	 * @return
	 */
	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	/**
	 * Function to evaluate the spreadsheet
	 */
		public void evaluation(){
			String inputLine=null;
			int count=0;
			while ((inputLine = fileProcessor.readFromFile()) != null) {
				count++;
				boolean isPresent=false;
				Cell curCell=null;
				boolean isCycle=false;
				String[] currLine = inputLine.split("=|\\+");
				for(int i=0;i<cellList.size();i++){
					if(cellList.get(i).getName().equals(currLine[0])){
						//////////////////////
						if(!isNumeric(currLine[1])){
							Cell op1=findCell(currLine[1], cellList);
							if(isCycle(cellList.get(i), op1)){
								//System.out.println("Cycle is present at line:"+count+" Cell line :"+inputLine);
								//Logger.writeMessage("Cycle is present at line:"+count+" Cell line :"+inputLine, DebugLevel.CYCLE);
								isCycle=true;
								break;
							}
						}
						if(!isNumeric(currLine[2])){
							Cell op1=findCell(currLine[2], cellList);
							if(isCycle(cellList.get(i), op1)){
								//System.out.println("Cycle is present at line:"+count+" Cell line :"+inputLine);
								//Logger.writeMessage("Cycle is present at line:"+count+" Cell line :"+inputLine, DebugLevel.CYCLE);
								isCycle=true;
								break;
							}
						}
						
						
						curCell=cellList.get(i);
						curCell.setValue(0);
						curCell.setOperand1(-1);
						curCell.setOperand2(-1);
						curCell.flag=false;
						//subjects should be set to null
						curCell.setSubjects();
						isPresent=true;
						break;
					}
				
				}
				if(isCycle){
					//System.out.println("cycle");
					Logger.writeMessage("Cycle Present at line :"+count+" and equation is :"+inputLine, DebugLevel.CYCLEPRESENT);
					continue;
				}
				if(!isPresent){
					curCell=new Cell();
					
				}
				
				curCell.setName(currLine[0]);
				if(isNumeric(currLine[1])&&isNumeric(currLine[2])){
					 int x=Integer.parseInt(currLine[1])+Integer.parseInt(currLine[2]);
					 curCell.setOperand1(Integer.parseInt(currLine[1]));
					 curCell.setOperand2(Integer.parseInt(currLine[2]));
					 curCell.setValue(x);
					 curCell.flag=true;
					
				}
				else{
					 
					if(!isNumeric(currLine[1])){
						boolean op1=false;
						for(int i=0;i<cellList.size();i++){
							if(cellList.get(i).getName().equals(currLine[1])){
				 			cellList.get(i).registerObserver(curCell);
				 			curCell.addSubjects(cellList.get(i));
				 			op1=true;
				 			break;
				 			
							} 
						}
						if(!op1){
							Cell op1Cell=new Cell();
							op1Cell.setName(currLine[1]);
							op1Cell.registerObserver(curCell);;
							curCell.addSubjects(op1Cell);
							cellList.add(op1Cell);
							Logger.writeMessage("The Cell is added:", DebugLevel.CELLCREATED);
							
						}
					}
					else{
							curCell.setOperand1(Integer.parseInt(currLine[1]));
					}
					if(!isNumeric(currLine[2])){
						boolean op2=false;
						for(int i=0;i<cellList.size();i++){
							if(cellList.get(i).getName().equals(currLine[2])){	
								cellList.get(i).registerObserver(curCell);
								curCell.addSubjects(cellList.get(i));
								op2=true;
								break;
							} 
						}if(!op2){
							Cell op2Cell=new Cell();
							op2Cell.setName(currLine[2]);
							op2Cell.registerObserver(curCell);;
							curCell.addSubjects(op2Cell);
							cellList.add(op2Cell);
							Logger.writeMessage("The Cell is added:", DebugLevel.CELLCREATED);
							
						}
						
					}
					else{
						curCell.setOperand2(Integer.parseInt(currLine[2]));
					}
				}	
				if(!isPresent){
					cellList.add(curCell);
					Logger.writeMessage("The Cell is added:", DebugLevel.CELLCREATED);
				}else{
					 for(int i=0;i<cellList.size();i++){
							if(cellList.get(i).getName().equals(currLine[0])){
								int value=curCell.getValue();
								cellList.get(i).setValue(value);	
								if(curCell.getOperand1()!=-1)
									cellList.get(i).setOperand1(curCell.getOperand1());
								if(curCell.getOperand2()!=-1)
									cellList.get(i).setOperand2(curCell.getOperand2());
								cellList.get(i).flag=curCell.flag;
								cellList.get(i).updateSubjectList(curCell.subjects);
								//cellList.get(i).updateObserversList(curCell.observers);
								
								
							}
					 }
				}
			}
			
			for(int i=0;i<cellList.size();i++){
				if(cellList.get(i).flag){
					cellList.get(i).notifyObservers();
					
				}
			}
			
			/*
			for(int i=0;i<cellList.size();i++){
				System.out.println(cellList.get(i).getName()+" "+cellList.get(i).getValue());
				
			}*/
			
			
			//System.out.println("The sum of all the cell is :"+getSum(cellList));
			
		}
		
		/**
		 * Method is used to check whether there is cycle between the cell due to dependencies
		 * @param observer cell
		 * @param subject to observer
		 * @return boolean
		 */
		public boolean isCycle(Cell obs,Cell subj){
			Iterator<Observer> it= obs.observers.iterator();
			
			while(it.hasNext()){
				Cell o=(Cell) it.next();
				if(isCycle(o,subj)){
					return true;
				}else if(o.getName().equals(subj.getName())){
					return true;
				}
			}
			return false;
		}

		/**
		 * Method for checking cell is present in main cell list
		 * @param String of cell to check
		 * @param Arraylist of cell
		 * @return cell
		 */
		public Cell findCell(String s,ArrayList<Cell> al){
			for(Cell c: al){
				if(c.getName().equals(s)){
					return c;
				}
			}
			throw new IllegalStateException(s+": is not present in arrayList");
		}
		
		
		/**
		 * method to evaluate the sum of all the cells in the spreadsheet 
		 * @param arraylist of cell
		 * @return integer sum
		 */
		public int getSum(ArrayList<Cell> al){
			int sum=0;
			
			for(Cell c:al){
				sum+=c.getValue();
			}
			return sum;
		}
		
		/**
		 * Method to print all the cells name ,value and overall sum to the output file 
		 * @param outputFileName
		 */
		public void writeSchedulesToFile(String outputFileName) {
			try {
				writer = new BufferedWriter(new FileWriter(outputFileName));
				for(int i=0;i<cellList.size();i++){
					//System.out.println(cellList.get(i).getName()+" "+cellList.get(i).getValue());
					Logger.writeMessage(cellList.get(i).getName()+" "+cellList.get(i).getValue() , DebugLevel.RUN);
					writer.write(cellList.get(i).getName()+" "+cellList.get(i).getValue() + "\n");
					
				}
				
				Logger.writeMessage("The sum of all cell values is:"+getSum(cellList), DebugLevel.NOOUTPUT);
				writer.write("\nThe sum of all cell values is:"+getSum(cellList)  + "\n\n");
				writer.flush();
			} catch (IOException e) {
				System.err.println("Exception occured in writeSchedulesToFile method.");
				e.printStackTrace();
				System.exit(1);
			}
		}
		
/**
* SpreadsheetClass toString Method 
*/
	@Override
	public String toString() {
		return "SpreadSheetExample [fileProcessor=" + fileProcessor + ", cellList=" + cellList + "]";
	}
		
		
		
}
