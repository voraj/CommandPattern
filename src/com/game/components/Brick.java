
package com.game.components;

import javax.swing.ImageIcon;

public class Brick extends Dimensions {

	boolean isDestroyed;
	/******************************************************************
	 * method - Brick
	 * date - 
	 * description - constructor of the class Brick initializes image, width,heigth
	 * input - x and y coordinates
	 * output - 
	 *******************************************************************/
	public Brick(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;

		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/brick.png"));
		image = imageIcon.getImage();

		width = image.getWidth(null);
		heigth = image.getHeight(null);

		isDestroyed = false;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}
        
        
        /******************************************************************
	 * method - Brick
	 * date - 
	 * description - setter method for Destroyed
	 * input - x and y coordinates
	 * output - the instance variable value of the object,isDestroyed
	 *******************************************************************/
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

}