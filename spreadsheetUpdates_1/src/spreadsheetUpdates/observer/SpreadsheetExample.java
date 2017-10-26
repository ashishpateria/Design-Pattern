package spreadsheetUpdates.observer;

import java.util.ArrayList;


import spreadsheetUpdates.util.FileProcessor;

public class SpreadsheetExample {

	private FileProcessor  fileProcessor;
	private ArrayList<Subject> cellList = new ArrayList<>();

	public SpreadsheetExample(FileProcessor fileProcessorIn) {

		fileProcessor = fileProcessorIn;
	}

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	public void evaluation() {

		String inputLine = null;
		
		while ((inputLine = fileProcessor.readFromFile()) != null) {
			boolean flag=false;
			
			//Subject cell = new Cell();
			Subject cell =null;
			String[] cellLine = inputLine.split("=|\\+");
			
			
			for(int i=0;i<cellList.size();i++){
				if(cellList.get(i).getName().equals(cellLine[0])){
					cell=cellList.get(i);
					((Cell)cell).setValue(0);
					((Cell) cellList.get(i)).khaliKaroArrayList();
					flag=true;
					break;
				}
			}
			if(!flag){
				cell=new Cell();
				
			}
			  
				
			 ((Cell)cell).setName(cellLine[0]);
			 if(isNumeric(cellLine[1])){
				 ((Cell)cell).addValue(Integer.parseInt(cellLine[1]));
				
			 }else{
				 	for(int i=0;i<cellList.size();i++){
				 		if(cellList.get(i).getName().contains(cellLine[1])){
				 			
				 			cellList.get(i).registerObserver((Observer) cell);
				 			
				 			break;
				 		} 
				 	}
			 }
			 if(isNumeric(cellLine[2])){
				  ((Cell)cell).addValue(Integer.parseInt(cellLine[2]));
				  	
			 }else{
				 	for(int i=0;i<cellList.size();i++){
				 		if(cellList.get(i).getName().contains(cellLine[2])){
				 			
				 				cellList.get(i).registerObserver((Observer) cell);
				 				
				 				break;
			  			 }
					 }
			 
			 }
			 if(isNumeric(cellLine[1])&&isNumeric(cellLine[2])){
				 ((Cell)cell).flag=true;
				
			}
			 if(!flag){
			 cellList.add(cell);
			 
			 }
			 else{
				 for(int i=0;i<cellList.size();i++){
						if(cellList.get(i).getName().contains(cellLine[0])){

							int value=((Cell)cell).getValue();
						
						 cellList.get(i).setValue(value);
						 
						}
				 
				 }
			 }
		}
		
		int sum=0;
		for(int i=0;i<cellList.size();i++){
			if(cellList.get(i).getValue() > 18){
				cellList.get(i).notifyObservers();
				//if(cellList.get(i).getValue() != sum)
					//sum+=cellList.get(i).getValue();
				
			}
		}
		
		for(int i=0;i<cellList.size();i++){
			if(cellList.get(i).getValue() > 18){
				//cellList.get(i).notifyObservers();
				//if(cellList.get(i).getValue() != sum)
					sum+=cellList.get(i).getValue();
				
			}
		}
		
		
		for(int i=0;i<cellList.size();i++){
			System.out.println(cellList.get(i).getName()+" "+cellList.get(i).getValue());
			
		}
		System.out.println(sum);
	}

}