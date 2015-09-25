package com.game.command;

public interface Command {
	/********************************* 
	 * Type - Interface Command for implementing Command Pattern
	 * date - 
	 * description - Encapsulates Move Function of all objects; useful for undo functionality
	 * input - NA
	 * output - NA
	 **********************************/
	public void execute();

	public void undo();

	public void redo();
}
