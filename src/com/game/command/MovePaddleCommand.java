package com.game.command;

import com.game.components.Paddle;

public class MovePaddleCommand implements Command {
	private Paddle paddle; //Initialization
	public int initialX, initialY; //Initialization

	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for paddle's initial X position
	 * input -
	 * output -
	 **********************************/
	public int getInitialX() {
		return initialX;
	}

	private int timeStamp;
	
	/********************************* 
	 * Type - constructor
	 * date - 
	 * description - initializes the paddle position and timestamp
	 * input - paddle, timestamp
	 * output -
	 **********************************/
	public MovePaddleCommand(Paddle paddle, int timeStamp) {
		this.paddle = paddle;
		this.timeStamp = timeStamp;
		initialX = paddle.getX();
		initialY = paddle.getY();
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
		getPaddle().movePaddle();
	}
	
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
	 * description - overridden method from class Command; sets the X,Y position of the Paddle to the previous X,Y position on Undo function; 
	 * input -
	 * output -
	 **********************************/
	@Override
	public void undo() {
		getPaddle().setX(initialX);
		getPaddle().setY(initialY);
		getPaddle().movePaddle();
	}
	

	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for paddle instance
	 * input -
	 * output -
	 **********************************/
	public Paddle getPaddle() {
		return paddle;
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - overridden method from class Command; sets the X,Y position of the Paddle to the previous X,Y position on Redo function; 
	 * input -
	 * output -
	 **********************************/
	@Override
	public void redo() {
		getPaddle().setX(initialX);
		getPaddle().setY(initialY);
		getPaddle().movePaddle();
	}
}