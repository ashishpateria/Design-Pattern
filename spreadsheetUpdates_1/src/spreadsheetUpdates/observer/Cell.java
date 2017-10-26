package spreadsheetUpdates.observer;

import java.util.ArrayList;

public class Cell implements Subject,Observer{
	private ArrayList<Observer> observers;
	private int value;
	private String  name;
	public boolean flag; // both the operands are literals
	private int prevValue;

	
	public Cell() {
		observers=new ArrayList<>();
		value=0;
		prevValue=0;
		flag=false;
		
	}
	
	
	public void getObservers(){
		for(int i=0;i<observers.size();i++){
			System.out.println(" "+observers.get(i));
		}
		
	}
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		int i=observers.indexOf(o);
		if(i>=0){
			observers.remove(o);
		}
		
	}
	public void setZero(){
		value=0;
	}
	public void khaliKaroArrayList(){
		
		//for(int i=0;i<observers.size();i++){
		//	observers.get(i).setZero();
		//}
		observers=new ArrayList<Observer>();
		
	}

	@Override
	public void notifyObservers() {
		for(int i=0;i<observers.size();i++){
			Observer obs=(Observer)observers.get(i);
			obs.update(value);
			
		}
		
	}

	// From Observer interface
	@Override
	public void update(int data) {
		
		this.value+=data;
			
	}

	@Override
	public boolean getFlag() {
		
		return flag;
	}
	
	
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void addValue(int x){
		value+=x;
	}


	@Override
	public String toString() {
		return "Cell  name=" + name + ", flag=" + flag
				+ ", prevValue=" + prevValue + "]";
	}
	
	

	

	
}
