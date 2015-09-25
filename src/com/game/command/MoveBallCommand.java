package com.game.command;

import com.game.components.Ball;

public class MoveBallCommand implements Command {
	public Ball ball; //Initialization

	private int timeStamp; //Initialization
	
	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for timestamp 
	 * input -
	 * output -
	 **********************************/
	public int getTimeStamp() {
		return timeStamp;
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - setter method for timestamp 
	 * input - timestamp value
	 * output -
	 **********************************/
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}

	private int initialX, initialY;

	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for initial value of X
	 * input -
	 * output -
	 **********************************/
	public int getInitialX() {
		return initialX;
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for initial value of Y
	 * input -
	 * output -
	 **********************************/
	public int getInitialY() {
		return initialY;
	}

	private int xDirection, yDirection;

	/********************************* 
	 * Type - constructor
	 * date - 
	 * description - sets the initial X,Y Position of the Ball; sets the X,Y Direction in which the ball is to be moved; sets timestamp 
	 * input - ball,timeStamp
	 * output -
	 **********************************/
	public MoveBallCommand(Ball ball, int timeStamp) {
		this.ball = ball;
		this.timeStamp = timeStamp;
		initialX = ball.getX();
		initialY = ball.getY();
		xDirection = ball.getXDir();
		yDirection = ball.getYDir();
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for Ball instance
	 * input -
	 * output -
	 **********************************/
	public Ball getBall() {
		return ball;
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - setter method for Ball 
	 * input - Ball
	 * output -
	 **********************************/
	public void setBall(Ball ball) {
		this.ball = ball;
	}

	
	/********************************* 
	 * Type - method
	 * date - 
	 * description - overridden method from class Command
	 * input -
	 * output -
	 **********************************/
	@Override
	public void execute() {
		ball.moveBall();
	}

	
	/********************************* 
	 * Type - method
	 * date - 
	 * description - overridden method from class Command; sets the X,Y position of the Ball to the previous X,Y position on Undo function; sets the X,Y Direction of the Ball to move to older direction
	 * input -
	 * output -
	 **********************************/
	@Override
	public void undo() {
		ball.setX(initialX);
		ball.setY(initialY);
		ball.setXDir(xDirection);
		ball.setYDir(yDirection);
		ball.moveBall();
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - overridden method from class Command; sets the X,Y position of the Ball to the previous X,Y position on Redo function; 
	 * input -
	 * output -
	 **********************************/
	@Override
	public void redo() {
		ball.setX(initialX);
		ball.setY(initialY);
		ball.moveBall();
	}
}
