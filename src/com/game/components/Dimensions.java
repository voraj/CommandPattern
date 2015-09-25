package com.game.components;

import java.awt.Image;
import java.awt.Rectangle;

public class Dimensions {

	protected int xCoordinate;
	protected int yCoordinate;
	protected int width;
	protected int heigth;
	public Image image;
	/******************************************************************  
	 * method - setX,getX,setY,getY
	 * date - 
	 * description - getter and setter methods for x and y coordinates
	 * input - x and y coordinates
	 * output - the instance variable value of the objects,X and Y
	 *******************************************************************/
	public void setX(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getX() {
		return xCoordinate;
	}

	public void setY(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getY() {
		return yCoordinate;
	}
        
        /******************************************************************  
	 * method - getWidth,getHeight
	 * date - 
	 * description - getter methods for x and y coordinates
	 * input - 
	 * output - the instance variable value of the objects,Width and Height
	 *******************************************************************/
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return heigth;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getRectangle() {
		return new Rectangle(xCoordinate, yCoordinate, image.getWidth(null), image.getHeight(null));
	}
}