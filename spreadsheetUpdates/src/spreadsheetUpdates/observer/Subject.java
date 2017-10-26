package spreadsheetUpdates.observer;

/**
 * Subject interface
 * @author ashishpateria
 *
 */
public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
