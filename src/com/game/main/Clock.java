package com.game.main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.command.MoveBallCommand;
import com.game.command.UpdateClockCommand;
import com.game.components.Time;

public class Clock extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private GameObservable gameObserver;
	private JLabel showTimer, maximumTime;
	private int division;
	Time time;
	static ArrayList<Integer> minutesList = new ArrayList<Integer>();
	static ArrayList<Integer> secondsList = new ArrayList<Integer>();
	static List<UpdateClockCommand> clockStateList = new ArrayList<UpdateClockCommand>();
	
	/********************************* 
	 * date - 
	 * description - Initializes the game and adds it to the JFrame.
	 * input -game
	 * output -
	 **********************************/
	public Clock(GameObservable game) {
		division = 1000 / BreakoutBall.BALLSPEED;
		time = new Time(division);
		this.gameObserver = game;
		showTimer = new JLabel(" TIMER : " + 0 + " : " + 0);
		maximumTime = new JLabel(" MAXIMUM TIME : 60 SEC ");
		add(showTimer);
		add(maximumTime);
		setBackground(Color.lightGray);
	}

	/********************************* 
	 * date - 
	 * description - show's the timer with the desired set time 
	 * input -
	 * output -
	 **********************************/
	@Override
	public void update(Observable observer, Object arg) {
		gameObserver = (GameObservable)observer;
		int timeStamp = gameObserver.getTime();
		UpdateClockCommand clockState = new UpdateClockCommand(time,timeStamp);
		clockState.execute();
		minutesList.add(time.getMinutes());
		secondsList.add(time.getSeconds());
		displayTime();
		if(clockState.getInitialSecond() != time.getSeconds()){
			clockStateList.add(clockState);
		}
	}
	
	void displayTime(){
		showTimer.setText(" TIME : " + time.getMinutes() + " : " + time.getSeconds());
	}
	
	void displayTime(int minutes, int seconds){
		showTimer.setText(" TIME : " + minutes + " : " + seconds);
	}
}
