package com.game.command;

import com.game.components.Time;

public class UpdateClockCommand implements Command {
	private Time timeTick;
    private int timeStamp;
    private int initialMinute, initialSecond, initialTimeStamp;
    
    public int getInitialMinute() {
		return initialMinute;
	}

	public int getInitialSecond() {
		return initialSecond;
	}
	
	public int getInitialTimeStamp() {
		return initialTimeStamp;
	}

	public UpdateClockCommand(Time timeTick,int timeStamp){
    	this.timeTick = timeTick;
    	this.timeStamp = timeStamp;
    	this.initialMinute = timeTick.getMinutes();
    	this.initialSecond = timeTick.getSeconds();
    	this.initialTimeStamp = timeStamp;
    }
	
    /********************************* 
	 * method - 
	 * date - 
	 * description - 
	 * input -
	 * output -
	 **********************************/
	@Override
	public void execute() {
		timeTick.tick(timeStamp);
	}

	@Override
	public void undo() {
		timeTick.setMinutes(initialMinute);
		timeTick.setSeconds(initialSecond);
		timeTick.tick(initialTimeStamp);
	}

	@Override
	public void redo() {

	}
}
