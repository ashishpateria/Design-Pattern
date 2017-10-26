package spreadsheetUpdates.observer;

import java.util.ArrayList;

import spreadsheetUpdates.util.Logger;
import spreadsheetUpdates.util.Logger.DebugLevel;

/**
 * Cell class to store the values of the cells
 * @author ashishpateria
 *
 */
public class Cell implements Subject,Observer{
	public ArrayList<Observer> observers;
	public ArrayList<Subject> subjects;
	private int value;
	private String  name;
	private int operand1;
	private int operand2;
	public boolean flag;
	/**
	 * initialize the cell
	 */
	public Cell() {
		Logger.writeMessage("Cellconstructor  called ", DebugLevel.CONSTRUCTOR);
		observers=new ArrayList<>();
		subjects=new ArrayList<>();
		value=0;
		operand1=-1;
		operand2=-1;
		flag=false;
		
	}
	/**
	 * Method to initialize a subjects of cell
	 */
	public void setSubjects(){
		subjects=new ArrayList<>();
	}
	/**
	 * getter setter for operands of cell
	 * @return
	 */
	public int getOperand1(){
		return operand1;
	}
	public void setOperand1(int x){
		
		operand1=x;
	}
	public int getOperand2(){
		return operand2;
	}
	public void setOperand2(int x){
		
		operand2=x;
	}
	
	/**
	 * getter settersfor value of a cell
	 * @integer
	 */
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * getter setter of Name field
	 * @ name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
/**
 * update method from observer called to notify updated values
 * @param value of the cell from Subject
 */
	@Override
	public void update(int value) {
		int subllistLen=subjects.size();
		int count=0;
		for(int i=0;i<subjects.size();i++){
			if(((Cell) subjects.get(i)).getValue()>0){
				count++;
			}
		}
		if(count==subllistLen &&!flag){
			if(operand1!=-1)
				this.value+=operand1;
			if(operand2!=-1)
				this.value+=operand2;
			for(int i=0;i<subjects.size();i++){
				this.value+=((Cell) subjects.get(i)).getValue();
				
			}
			this.flag=true;
		}
		
		
	}

	public void addValue(int x){
		value+=x;
	}
	
	/**
	 * register method from Subject to register a observer
	 * @param observer
	 */
	@Override
	public void registerObserver(Observer o) {
		
		observers.add(o);
	}

	public void addSubjects(Subject s){
		subjects.add(s);
	}
	/**
	 * remove observer method from Subject class
	 */
	@Override
	public void removeObserver(Observer o) {
		int i=observers.indexOf(o);
		if(i>=0){
			observers.remove(o);
		}
		
	}

	/**
	 * notifies each observer with updated values
	 */
	@Override
	public void notifyObservers() {
		for(int i=0;i<observers.size();i++){
			Observer obs=(Observer)observers.get(i);
			obs.update(value);
			
		}
		
	}
	public void updateSubjectList(ArrayList<Subject> list){
		subjects=list;
		
	}
	public void updateObserversList(ArrayList<Observer> list){
		observers=list;
	}

	/**
	 * Cell class toString Method
	 */
	@Override
	public String toString() {
		return "Cell [observers=" + observers + ", subjects=" + subjects + ", value=" + value + ", name=" + name
				+ ", operand1=" + operand1 + ", operand2=" + operand2 + ", flag=" + flag + "]";
	}

	
}
