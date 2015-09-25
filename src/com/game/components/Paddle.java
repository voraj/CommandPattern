
package com.game.components;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.game.support.Constants;

public class Paddle extends Dimensions implements Constants {

	int dx;

	/******************************************************************
	 * type - constructor method 
	 * date - 
	 * description - constructor of the paddle class.
	 * Initializes the image,width and height 
	 * input - 
	 * output -
	 *******************************************************************/
	public Paddle() {

		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/paddle.png"));
		image = imageIcon.getImage();

		width = image.getWidth(null);
		heigth = image.getHeight(null);

		resetState();
	}

	/******************************************************************
	 * type - method 
	 * date - 
	 * description - 
	 * input - 
	 * output -
	 *******************************************************************/
	public void movePaddle() {
		xCoordinate += dx;
		if (xCoordinate <= 2)
			xCoordinate = 2;
		if (xCoordinate >= Constants.PADDLE_RIGHT)
			xCoordinate = Constants.PADDLE_RIGHT;
	}

	/******************************************************************
	 * method - keyPressed 
	 * date - 
	 * description - keylistener to check the paddle movement. If the paddle 
	 * 				 is moved left, then a difference of -2 is set and
	 * 				 if the paddle is moved right, then a difference of 2 is set. 
	 * input - KeyEvent 
	 * output -
	 *******************************************************************/
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;

		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
            movePaddleLeft(dx);
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
            movePaddleRight(dx);
		}
	}
    
	/******************************************************************
	 * method - movePaddleRight and movePaddleLeft 
	 * date - 
	 * description - increments and decrements the x-coordinate based on the paddle movement.  
	 * 
	 *  
	 * input - KeyEvent 
	 * output -
	 *******************************************************************/
	
	public void movePaddleRight(int dx2) {
    	getX();
    	setX(xCoordinate+= dx2);			
	}

	public void movePaddleLeft(int dx2) {
    	getX();
    	setX(xCoordinate+= dx2);			
	}
	
	public void resetState() {
		xCoordinate = 200;
		yCoordinate = 500;
	}
}