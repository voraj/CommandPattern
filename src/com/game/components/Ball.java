
package com.game.components;

import javax.swing.ImageIcon;

import com.game.support.Constants;

public class Ball extends Dimensions implements Constants {

	private int xDirection;
	private int yDirection;
	/******************************************************************
	 * method - 
	 * date - 
	 * description - constructor of the Ball class initializes the x and y direction
			 and image,width,heigth
	 * input - 
	 * output - 
	 *******************************************************************/
	public Ball() {

		xDirection = 1;
		yDirection = -1;

		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/ball.jpg"));
		image = imageIcon.getImage();

		width = image.getWidth(null);
		heigth = image.getHeight(null);

		resetState();
	}
        /******************************************************************
	 * method - moveBall
	 * date - 
	 * description - moves the ball in the x and y coordinate based on the direction.
			 also checks if the ball hits the wall and it reduces the x-				 direction of the ball.
	 * input -
	 * output -
	 *******************************************************************/
	public void moveBall() {
		xCoordinate += xDirection;
		yCoordinate += yDirection;

		if (xCoordinate == 0) {
			setXDir(1);
		}

		if (xCoordinate == BALL_RIGHT) {
			setXDir(-1);
		}

		if (yCoordinate == 0) {
			setYDir(1);
		}
	}

	public void resetState() {
		xCoordinate = 230;
		yCoordinate = 479;
	}
        
        /******************************************************************
	 * method - 
	 * date - 
	 * description - getter and setter methods of the x and y direction respectively.
	 * input - x and y coordinate
	 * output - this instance’s x and y direction values.
	 *******************************************************************/
	public void setXDir(int x) {
		xDirection = x;
	}

	public void setYDir(int y) {
		yDirection = y;
	}

	public int getXDir() {
		return xDirection;
	}

	public int getYDir() {
		return yDirection;
	}
}