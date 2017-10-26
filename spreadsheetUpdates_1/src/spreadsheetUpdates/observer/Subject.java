package spreadsheetUpdates.observer;

public interface Subject {

	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	public String getName();
	public int getValue();
	public boolean getFlag();
	public void  setValue(int x);
	public void getObservers();
}
