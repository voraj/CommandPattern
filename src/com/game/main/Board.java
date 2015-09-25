
package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.game.command.BrickStatusCommand;
import com.game.command.MoveBallCommand;
import com.game.command.MovePaddleCommand;
import com.game.components.Ball;
import com.game.components.Brick;
import com.game.components.Paddle;
import com.game.support.Constants;

public class Board extends JPanel implements Constants, Observer {

	private static final long serialVersionUID = 1L;
	GameObservable gameObserver;
	Image image;
	String message = "Game Over";
	Ball ball;
	Paddle paddle;
	Brick bricks[];
	int numberOfBricks = 30, timerId;
	
	boolean paddleCollide = false, brickCollide = false;
	static boolean INGAME = true, REPLAY = false;
	static int arrCnt = 0;
	
	//List for storing states of components
	static List<MoveBallCommand> ballStateList = new ArrayList<MoveBallCommand>();
	static List<MovePaddleCommand> paddleStateList = new ArrayList<MovePaddleCommand>();
	static List<BrickStatusCommand> brickStateList = new ArrayList<BrickStatusCommand>();
	
	static ArrayList<Integer> ballListX = new ArrayList<Integer>();
	static ArrayList<Integer> ballListY = new ArrayList<Integer>();
	static ArrayList<Integer> paddleListX = new ArrayList<Integer>();
	static ArrayList<TemporaryBrick> brickList = new ArrayList<TemporaryBrick>();

	/*********************************
	 * type - constructor method
	 * date - 
	 * description - Constructor for Board class
	 * input - GameObservable
	 * output - 
	 **********************************/
	public Board(GameObservable observer) {
		this.gameObserver = observer;
		gameObserver.addObserver(this);
		bricks = new Brick[numberOfBricks];
		setDoubleBuffered(true);
		//add key listener for capturing paddle movements
		addKeyListener(new customizedKeyListener());
		setFocusable(true);
	}

	/********************************* 
	 * type - class
	 * date - 
	 * description - contains KeyListener method to capture key events for paddle movement
	 * input -
	 * output -
	 **********************************/
	class customizedKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			paddle.keyPressed(e);

		}

		@Override
		public void keyReleased(KeyEvent e) {
			paddle.keyReleased(e);

		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
	}

	/*********************************
	 * type - method
	 * date - 
	 * description - add Board as observer
	 * input - GameObservable
	 * output - 
	 **********************************/
	public void addNotify() {
		super.addNotify();
		initializeGame();
	}
	
	/*
	/*********************************
	 * type - method
	 * date - 
	 * description - Replay functionality, get State of each component one by one from the list and repaint with new vales
	 * input - MoveBallCommand, MovePaddleCommand, BrickStatusCommand
	 * output - 
	 **********************************
	public void replay(MoveBallCommand ballRState, MovePaddleCommand paddleRState, BrickStatusCommand brickRState) {
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ball.setX(ballRState.getInitialX());
		ball.setY(ballRState.getInitialY());
		paddle.setX(paddleRState.getInitialX());
		brickRState.redo();
		repaint();
	}*/

	/********************************* 
	 * type - method
	 * date - 
	 * description - initialize components of board
	 * input -
	 * output -
	 **********************************/
	public void initializeGame() {

		ball = new Ball();
		paddle = new Paddle();
		int brickCounter = 0;
		for (int rowNumber = 0; rowNumber < 6; rowNumber++) {
			for (int columnNumber = 0; columnNumber < 5; columnNumber++) {
				bricks[brickCounter] = new Brick(columnNumber * 50 + 60, rowNumber * 20 + 50);
				brickCounter++;
			}
		}
	}

	/********************************* 
	 * type - method 
	 * date - 
	 * description - method to draw board with all its components
	 * input - Graphics
	 * output -
	 * Reference - The following code is a derivative work of the code from 
	 * 				website Zet Code - Java Game Tutorials 
	 * 				<http://zetcode.com/tutorials/javagamestutorial/breakout/> 
	 **********************************/
	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (INGAME || REPLAY) {
			graphics.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight(), this);
			graphics.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(),
					this);

			for (int brickCounter = 0; brickCounter < numberOfBricks; brickCounter++) {
				if (!bricks[brickCounter].isDestroyed())
					graphics.drawImage(bricks[brickCounter].getImage(), bricks[brickCounter].getX(),
							bricks[brickCounter].getY(), bricks[brickCounter].getWidth(),
							bricks[brickCounter].getHeight(), this);
			}
		} else {
			//Print message for Game Over or Victory
			Font font = new Font("Verdana", Font.BOLD, 18);
			FontMetrics metrics = this.getFontMetrics(font);
			graphics.setColor(Color.BLACK);
			graphics.setFont(font);
			graphics.drawString(message, (Constants.WIDTH - metrics.stringWidth(message)) / 2, Constants.WIDTH / 2);
		}

		Toolkit.getDefaultToolkit().sync();
		graphics.dispose();
	}

	/********************************* 
	 * type - method
	 * date - 
	 * description - changes INGAME variable to false, this makes game status as stopped 
	 * input -
	 * output -
	 **********************************/
	public void stopGame() {
		INGAME = false;
	}

	/********************************* 
	 * type - method
	 * date - 
	 * description - method check for collisions of ball with bricks, paddle and wall 
	 * input -
	 * output -
	 * Reference - The following code is a derivative work of the code from 
	 * 				website Zet Code - Java Game Tutorials 
	 * 				<http://zetcode.com/tutorials/javagamestutorial/breakout/> 
	 **********************************/
	public void checkCollision() {
		
		//if position of ball is below than stated then over the game
		if (ball.getRectangle().getMaxY() > Constants.BOTTOM) {
			stopGame();
		}
		
		//if all the bricks are destroyed then user wins
		for (int brickCounter = 0, numberOfDestroyedBricks = 0; brickCounter < numberOfBricks; brickCounter++) {
			if (bricks[brickCounter].isDestroyed()) {
				numberOfDestroyedBricks++;
			}
			if (numberOfDestroyedBricks == numberOfBricks) {
				message = "Victory";
				stopGame();
			}
		}

		//if ball's position is overlapping paddle then check the position of collision
		if ((ball.getRectangle()).intersects(paddle.getRectangle())) {

			int paddleLPos = (int) paddle.getRectangle().getMinX();
			int ballLPos = (int) ball.getRectangle().getMinX();
			//set flag for paddle and ball collision 
			paddleCollide = true;
			int first = paddleLPos + 8;
			int second = paddleLPos + 16;
			int third = paddleLPos + 24;
			int fourth = paddleLPos + 32;
			
			//decide direction of ball based on the position of collision
			if (ballLPos < first) {
				ball.setXDir(-1);
				ball.setYDir(-1);
			}

			if (ballLPos >= first && ballLPos < second) {
				ball.setXDir(-1);
				ball.setYDir(-1 * ball.getYDir());
			}

			if (ballLPos >= second && ballLPos < third) {
				ball.setXDir(0);
				ball.setYDir(-1);
			}

			if (ballLPos >= third && ballLPos < fourth) {
				ball.setXDir(1);
				ball.setYDir(-1 * ball.getYDir());
			}

			if (ballLPos > fourth) {
				ball.setXDir(1);
				ball.setYDir(-1);
			}
		}
		
		//check for collision with bricks and set status of brick if collided with ball
		for (int i = 0; i < 30; i++) {
			if ((ball.getRectangle()).intersects(bricks[i].getRectangle())) {

				int ballLeft = (int) ball.getRectangle().getMinX();
				int ballHeight = (int) ball.getRectangle().getHeight();
				int ballWidth = (int) ball.getRectangle().getWidth();
				int ballTop = (int) ball.getRectangle().getMinY();
				brickCollide = true;
				Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointLeft = new Point(ballLeft - 1, ballTop);
				Point pointTop = new Point(ballLeft, ballTop - 1);
				Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
				
				//set direction of ball based on the brick position
				if (!bricks[i].isDestroyed()) {
					if (bricks[i].getRectangle().contains(pointRight)) {
						ball.setXDir(-1);
					}

					else if (bricks[i].getRectangle().contains(pointLeft)) {
						ball.setXDir(1);
					}

					if (bricks[i].getRectangle().contains(pointTop)) {
						ball.setYDir(1);
					}

					else if (bricks[i].getRectangle().contains(pointBottom)) {
						ball.setYDir(-1);
					}

					bricks[i].setDestroyed(true);
				}
			}
		}
	}

	/********************************* 
	 * type - method
	 * date - 
	 * description - method gets invoke on change in observable value, 
	 * 				 saves states of all components of board and redraw board with updated values 
	 * input - Observable
	 * output -
	 **********************************/
	@Override
	public void update(Observable observable, Object obj) {
		if (!REPLAY) {
			gameObserver = (GameObservable) observable;
			int time = gameObserver.getTime();
			if (time == 12000) {
				INGAME = false;
			}
			
			//Request ball movement and Save the state of ball 
			MoveBallCommand ballState = new MoveBallCommand(ball, time);
			ballState.execute();
			ballStateList.add(ballState);

			//Request paddle movement
			MovePaddleCommand paddleState = new MovePaddleCommand(paddle, time);
			paddleState.execute();
			
			checkCollision();
			// Save the state of paddle if collision has happened
			if (paddleCollide) {
				paddleStateList.add(paddleState);
				paddleCollide = false;
			}
			// Save the state of brick if collision has happened
			if (brickCollide) {
				BrickStatusCommand brickState = new BrickStatusCommand(bricks, time);
				brickStateList.add(brickState);
				brickCollide = false;
			}

		}
		
		//Add states of components for replay
		ballListX.add(ball.getX());
		ballListY.add(ball.getY());
		paddleListX.add(paddle.getX());
		TemporaryBrick tempBrick = new TemporaryBrick();
		for(int i=0; i<30; i++){
			tempBrick.tempBrick[i] = bricks[i].isDestroyed();
		}
		brickList.add(tempBrick);
		repaint();
	}
	
	
	class TemporaryBrick{
		boolean[] tempBrick = new boolean[30];
	}

	/********************************* 
	 * type - method
	 * date - 
	 * description - prints board with values saved in respective state arrays of the components of board
	 * input -
	 * output -
	 **********************************/
	void printBoardReplay(){
		//redraw board with updated values
		update(getGraphics());
	}
}