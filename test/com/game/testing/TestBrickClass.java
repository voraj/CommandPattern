/* TestBrickClass.java : Unit test for checking if the isDestroyed property associated with a brick is being set correctly with respect to each
 * brick in the arraylist.
 * 
 * Part of: BreakoutGame: Assignment #2 for P532
 * 
 * Created On: 09/13/2015
 * JIRA ISSUE : PFTA-4 - Create JUnit test cases (http://tintin.cs.indiana.edu:8091/browse/PFTA-4)
 */

package com.game.testing;

import org.junit.Assert;
import org.junit.Test;
import com.game.components.Brick;

public class TestBrickClass extends Assert {

	@Test
	public void testBrick() {
        boolean isDestroyed = true;
        boolean expected0DestroyedProperty =  true;
        boolean expected7DestroyedProperty =  false;
        int numberOfBricks = 30;
        int brickCounter = 0;
        Brick brickObj[];
         brickObj = new Brick[numberOfBricks];
        for (int rowNumber = 0; rowNumber < 6; rowNumber++) {
            for (int columnNumber = 0; columnNumber < 5; columnNumber++) {
            	brickObj[brickCounter] = new Brick(columnNumber * 50 + 60, rowNumber * 20 + 50);
                brickCounter++;
            }
        }
        brickObj[0].setDestroyed(isDestroyed);
        
        /* 
        * This assert should return (true,true) since for the 0th brick the setDestroyed property of the brick is explicitly set to true
        */
        assertEquals(expected0DestroyedProperty, brickObj[0].isDestroyed());
        
        /* 
 		* This assert should return (false,false) since the default property of this brick should be false which is being set in the brick class         
        */
        assertEquals(expected7DestroyedProperty, brickObj[7].isDestroyed());
    } 
    
}
