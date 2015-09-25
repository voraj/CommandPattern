/* TestClassInstance.java : Unit test for checking if the subclasses inherit the base class object.
 * 							Uses Hamcrest framework which allows match rules to be defined 
 *                  
 * 
 * Part of: BreakoutGame: Assignment #2 for P532
 * 
 * Created On: 09/13/2015
 * JIRA ISSUE : PFTA-4 - Create JUnit test cases (http://tintin.cs.indiana.edu:8091/browse/PFTA-4)

 */
package com.game.testing;
import com.game.components.Ball;
import com.game.components.Brick;
import com.game.components.Dimensions;
import com.game.components.Paddle;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;
public class TestClassInstance {

	@Test
    public void testInstanceOf() {
        int actualXCoordinate= 400;
        int actualYCoordinate = 400;
		Brick brickClass = new Brick(actualXCoordinate,actualYCoordinate);
		Paddle paddleClass = new Paddle();
		Ball ballClass = new Ball();
        assertThat(brickClass,  instanceOf(Dimensions.class));
        assertThat(paddleClass, instanceOf(Dimensions.class));
        assertThat(ballClass, instanceOf(Dimensions.class));
    }
}
