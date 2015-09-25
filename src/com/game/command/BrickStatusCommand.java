package com.game.command;

import com.game.components.Brick;
import com.game.main.Board;

public class BrickStatusCommand implements Command {

	Brick[] brick; //Initialization
	boolean[] destroyedBricks = new boolean[30]; //Initialization
	
	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for destroyed bricks
	 * input -
	 * output -
	 **********************************/
	public boolean[] getDestroyedBricks() {
		return destroyedBricks;
	}

	Board board;
	
	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for bricks
	 * input -
	 * output -
	 **********************************/
	public Brick[] getBrick() {
		return brick;
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - setter method for bricks
	 * input -
	 * output -
	 **********************************/
	public void setBrick(Brick[] brick) {
		this.brick = brick;
	}

	int timestamp;

	/********************************* 
	 * Type - constructor
	 * date - 
	 * description - 
	 * input - bricks,timeStamp
	 * output -
	 **********************************/
	public BrickStatusCommand(Brick[] brick, int timeStamp) {
		this.brick = brick;

		for (int i = 0; i < 30; i++) {
			this.destroyedBricks[i] = brick[i].isDestroyed();
		}
		this.timestamp = timeStamp;
	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - getter method for timestamp
	 * input -
	 * output -
	 **********************************/
	public int getTimestamp() {
		return timestamp;
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

	}

	/********************************* 
	 * Type - method
	 * date - 
	 * description - overridden method from class Command; creates a copy of the destroyedBricks Array
	 * input -
	 * output -
	 **********************************/
	@Override
	public void undo() {
		for (int i = 0; i < 30; i++) {
			brick[i].setDestroyed(destroyedBricks[i]);
		}
	}

	
	/********************************* 
	 * Type - method
	 * date - 
	 * description - overridden method from class Command; 
	 * input -
	 * output -
	 **********************************/
	@Override
	public void redo() {
		for (int i = 0; i < 30; i++) {
			brick[i].setDestroyed(destroyedBricks[i]);
		}
	}
}