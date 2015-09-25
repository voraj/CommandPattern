
/* TestPaddleListener.java : Unit test for checking the keyboard movement of the paddle. This is achieved
 * by testing if the movePaddleLeft and movePaddleRight methods in Paddle class are working as expected.
 * 
 * Part of: BreakoutGame: Assignment #2 for P532
 * 
 * Created On: 09/13/2015
 * JIRA ISSUE : PFTA-4 - Create JUnit test cases (http://tintin.cs.indiana.edu:8091/browse/PFTA-4) 
 */

package com.game.testing;

import org.junit.Test;
import com.game.components.Paddle;
import org.junit.Assert;
 
public class TestPaddleListener extends Assert {

   //Test for the paddle's left movement
        @Test
   public void paddleMoveLeftTest() {
            int actualXCoordinate= 200;
        	int moveLeft = -2;
            int expectedXCoordinate =  actualXCoordinate + moveLeft;
            Paddle paddleEvent = new Paddle();
            paddleEvent.setX(actualXCoordinate);
            paddleEvent.movePaddleLeft(moveLeft);         
            assertEquals(expectedXCoordinate, paddleEvent.getX());
        }
        
   //Test for the paddle's right movement   
        @Test
        public void paddleMoveRightTest() {
            int actualXCoordinate= 200;
        	int moveRight = 2;
            int expectedXCoordinate =  actualXCoordinate + moveRight;
            Paddle paddleEvent = new Paddle();
            paddleEvent.setX(actualXCoordinate);
            paddleEvent.movePaddleRight(moveRight);         
            assertEquals(expectedXCoordinate, paddleEvent.getX());
        }
       
}
