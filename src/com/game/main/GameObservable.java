package com.game.main;

import java.util.Observable;

public class GameObservable extends Observable {

	private int time;

	/********************************* 
     * date - 
	 * description - Initializes the time when GameObservable object is created.
	 * input -time
	 * output -
	 **********************************/
	public GameObservable(int time) {
		this.time = time;
	}

	/********************************* 
	 * date - 
	 * description -Gets the time 
	 * input -
	 * output -
	 **********************************/
	public int getTime() {
		return time;
	}

	/********************************* 
	 * date - 
	 * description - sets the Time and notifies the Observers.
	 * input -time
	 * output -
	 **********************************/
	public void setTime(int time) {
		this.time = time;
		setChanged();
		notifyObservers();
	}
}
