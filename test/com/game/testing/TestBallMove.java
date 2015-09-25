
/* TestPaddleListener.java : Unit test for checking if the moveBall method in the Ball class is actually 
 * incrementing the xCoordinate and yCoordinate based on the direction of the ball.
 * 
 * Part of: BreakoutGame: Assignment #2 for P532
 * 
 * Created On: 09/13/2015
 * JIRA ISSUE : PFTA-4 - Create JUnit test cases (http://tintin.cs.indiana.edu:8091/browse/PFTA-4) 
 */

package com.game.testing;

import org.junit.Test;

import com.game.components.Ball;
import org.junit.Assert;
 
public class TestBallMove extends Assert {
	
	//Test for the Ball's x-direction movement
        @Test
   public void testBallMoveXDirection() {
            int actualXDirection= 10;
            int actualXCoordinate = 400;
            int expectedXCoordinate =  actualXCoordinate + actualXDirection;
            Ball ballMove = new Ball();
            ballMove.setXDir(actualXDirection);
            ballMove.setX(actualXCoordinate);
            ballMove.moveBall();
            assertEquals(expectedXCoordinate, ballMove.getX());
        } 
        
   //Test for the Ball's y-direction movement
        @Test
  public void testBallMoveYDirection() {
            int actualYDirection= -10;
            int actualYCoordinate = 500;
            int expectedYCoordinate =  actualYCoordinate + actualYDirection;
            Ball ballMove = new Ball();
            ballMove.setYDir(actualYDirection);
            ballMove.setY(actualYCoordinate);
            ballMove.moveBall();
            assertEquals(expectedYCoordinate, ballMove.getY());
        }
       
}
