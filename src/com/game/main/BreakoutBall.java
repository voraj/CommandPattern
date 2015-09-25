package com.game.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.game.command.BrickStatusCommand;
import com.game.command.MoveBallCommand;
import com.game.command.MovePaddleCommand;
import com.game.command.UpdateClockCommand;
import com.game.main.Board.TemporaryBrick;
import com.game.support.Constants;

public class BreakoutBall extends JFrame {

	private static final long serialVersionUID = 1L;

	private GameObservable gameObserver;
	private Board board;
	private Clock clock;
	private Timer timer;
	public static int BALLSPEED = 5;
	private JButton startStopButton, pauseResumeButton, undoButton, replayButton;

	/****************************************************************************
	 * date - 
	 * description - Adds the components to the Jpanel and aligns  the different 
	 *   			 panels on the board at the specific desired locations.	                 
	 * input -
	 * output -
	 *****************************************************************************/
	public void drawBoard() {

		// Create Observable variable
		gameObserver = new GameObservable(0);

		// Add panel with buttons Start/Stop, Pause/Resume, Undo and Replay
		JPanel buttonPanel = new JPanel();

		startStopButton = new JButton();
		startStopButton.setText("Start");
		startStopButton.addMouseListener(new customMouseListener());
		startStopButton.setFocusable(false);

		pauseResumeButton = new JButton();
		pauseResumeButton.setText("Pause");
		pauseResumeButton.addMouseListener(new customMouseListener());
		pauseResumeButton.setFocusable(false);
		pauseResumeButton.setEnabled(false);

		undoButton = new JButton();
		undoButton.setText("Undo");
		undoButton.addMouseListener(new customMouseListener());
		undoButton.setFocusable(false);
		undoButton.setEnabled(false);

		replayButton = new JButton();
		replayButton.setText("Replay");
		replayButton.addMouseListener(new customMouseListener());
		replayButton.setFocusable(false);
		replayButton.setEnabled(false);

		// Add buttons to the panel
		buttonPanel.add(startStopButton);
		buttonPanel.add(pauseResumeButton);
		buttonPanel.add(undoButton);
		buttonPanel.add(replayButton);

		// Add Panels
		setLayout(new BorderLayout());
		clock = new Clock(gameObserver);
		add(clock, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		board = new Board(gameObserver);
		board.setBackground(Color.white);
		add(board);

		// Register to observer
		gameObserver.addObserver(board);
		gameObserver.addObserver(clock);

		// JFrame Properties
		setTitle("Breakout");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Constants.WIDTH, Constants.HEIGTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	/*************************************************************************
	 * date - 
	 * description - Starts the clock on the invocation of start button which 
	 *			 starts the game.           
	 * input -
	 * output -
	 *************************************************************************/
	private void startGame() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), 5, BreakoutBall.BALLSPEED);
	}

	/***************************************************************
	 * date - 
	 * description - Pauses the game when this button is invoked.
	 * input -
	 * output -
	 ******************************************************************/
	private void pauseGame() {
		timer.cancel();
	}

	/**************************************************************
	 * date - 
	 * description - Stops the game when this button is invoked.
	 * input -
	 * output -
	 ****************************************************************/
	private void stopGame() {
		timer.cancel();
		board.stopGame();
		replayButton.setEnabled(true);
		// to do - stopGame imp
	}

	/*************************************************************************
	 * date - 
	 * description - Main class which invokes the game.
	 * input -
	 * output -
	 **************************************************************************/
	public static void main(String[] args) {
		BreakoutBall breakOut = new BreakoutBall();
		breakOut.drawBoard();
	}

	/*******************************************************************************
	 * date - 
	 * description - This method is invoked when we schedule the task through timer.
	 * input -
	 * output -
	 ******************************************************************************/
	class ScheduleTask extends TimerTask {

		public void run() {
			if (Board.INGAME) {
				gameObserver.setTime(gameObserver.getTime() + 1);
			}else{
				replayButton.setEnabled(true);
				startStopButton.setEnabled(false);
				pauseResumeButton.setEnabled(false);
				undoButton.setEnabled(false);
			}
		}
	}

	/*******************************************************************************
	 * date - 
	 * description -returns the last stored ballstate  i.e. the directions and the 
	 *              locations. 
	 * input - timestamp
	 * output -ball state
	 *****************************************************************************/
	private MoveBallCommand getBallStateFromLast(int timeStamp) {
		MoveBallCommand ballState = null;
		int counter = 1;
		int stateSize = Board.ballStateList.size();
		while (counter <= stateSize) {
			ballState = Board.ballStateList.get(stateSize - counter);
			counter++;
			if (ballState.getTimeStamp() <= timeStamp) {
				break;
			}
		}
		return ballState;
	}

	/************************************************************************************
	 * date - 
	 * description-It starts seraching the state of the ball from beginning for the 
	 *             specified time stamp.
	 * input -timestamp
	 * output -ballstate
	 ************************************************************************************/
	@SuppressWarnings("unused")
	private MoveBallCommand getBallStateFromFirst(int timeStamp) {
		MoveBallCommand ballState = null;
		int counter = 0;
		int stateSize = Board.ballStateList.size();
		while (stateSize != counter) {
			ballState = Board.ballStateList.get(counter);
			counter++;
			if (ballState.getTimeStamp() <= timeStamp) {
				break;
			}
		}
		return ballState;
	}

	/**********************************************************************************
	 * date - 
	 * description - gets the latest stored state of the paddle object.
	 * input -timestamp.
	 * output -
	 **********************************************************************************/
	@SuppressWarnings("unused")
	private MovePaddleCommand getPaddleStateFromLast(int timeStamp) {
		MovePaddleCommand paddleState = null;
		int counter = 1;
		int stateSize = Board.paddleStateList.size();
		while (counter <= stateSize) {
			paddleState = Board.paddleStateList.get(stateSize - counter);
			counter++;
			if (paddleState.getTimeStamp() <= timeStamp) {
				break;
			}
		}
		return paddleState;
	}

	/********************************************************************************
	 * date - 
	 * description - gets the state of the paddle from the beginning for the timestamp .
	 * input -
	 * output -
	 ********************************************************************************/
	@SuppressWarnings("unused")
	private MovePaddleCommand getPaddleStateFromFirst(int timeStamp) {
		MovePaddleCommand paddleState = null;
		int counter = 0;
		int stateSize = Board.paddleStateList.size();
		while (stateSize != counter) {
			paddleState = Board.paddleStateList.get(counter);
			counter++;
			if (paddleState.getTimeStamp() <= timeStamp) {
				break;
			}
		}
		return paddleState;
	}

	/*********************************************************************************
	 * date - 
	 * description -gets the latest active and inactive bricks position of the 
	 *               latest stored state for the   
	 * input -
	 * output -
	 ********************************************************************************/
	public BrickStatusCommand getBrickStatusFromLast(int timeStamp) {
		BrickStatusCommand brickState = null;
		int counter = 1;
		int stateSize = Board.brickStateList.size();
		while (counter <= stateSize) {
			brickState = Board.brickStateList.get(stateSize - counter);
			counter++;
			if (brickState.getTimestamp() <= timeStamp) {
				break;
			}
		}
		return brickState;
	}

	/*********************************************************************************
	 * date - 
	 * description - gets the active and inactive bricks state from the beginning. 
	 * input -     timestamp.
	 * output -
	 ********************************************************************************/
	public BrickStatusCommand getBrickStatusFromFirst(int timeStamp) {
		BrickStatusCommand brickState = null;
		int counter = 0;
		int stateSize = Board.brickStateList.size();
		while (stateSize != counter) {
			brickState = Board.brickStateList.get(counter);
			counter++;
			if (brickState.getTimestamp() <= timeStamp) {
				break;
			}
		}
		return brickState;
	}

	public UpdateClockCommand getClockStatusFromLast(int timeStamp) {
		UpdateClockCommand clockState = null;
		int counter = 1;
		int stateSize = Clock.clockStateList.size();
		while (counter <= stateSize) {
			clockState = Clock.clockStateList.get(stateSize - counter);
			counter++;
			if (clockState.getInitialTimeStamp() <= timeStamp) {
				break;
			}
		}
		return clockState;
	}

	/*********************************************************************************
	 * date - 
	 * description - invokes the specific functionality as desired by the user on 
	 *              mouse click.
	 * input -
	 * output -
	 *********************************************************************************/
	class customMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent action) {
			JButton button = (JButton) action.getSource();
			String label = button.getText();
			switch (label) {

			case "Start":
				startGame();
				startStopButton.setText("Stop");
				pauseResumeButton.setEnabled(true);
				undoButton.setEnabled(true);
				break;

			case "Stop":
				stopGame();
				startStopButton.setText("Start");
				pauseResumeButton.setEnabled(false);
				undoButton.setEnabled(false);
				break;

			case "Pause":
				pauseGame();
				pauseResumeButton.setText("Resume");
				undoButton.setEnabled(false);
				break;

			case "Resume":
				pauseResumeButton.setText("Pause");
				undoButton.setEnabled(true);
				startGame();
				break;

			case "Undo":
				pauseGame();
				MovePaddleCommand paddleState = Board.paddleStateList.get(Board.paddleStateList.size() - 1);
				MoveBallCommand ballState = getBallStateFromLast(paddleState.getTimeStamp()- 100);
				BrickStatusCommand brickState = getBrickStatusFromLast(paddleState.getTimeStamp());
				UpdateClockCommand clockState = getClockStatusFromLast(paddleState.getTimeStamp()-100);
				ballState.undo();
				paddleState.undo();
				brickState.undo();
				clockState.undo();
				clock.displayTime();
				//board.updateBricks(brickState.getDestroyedBricks());
				startGame();
				break;

			case "Replay":
				Board.REPLAY = true;
				startStopButton.setEnabled(false);
				printReplay();
				break;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}
	public void printReplay() {
		int count = 0;
		for (count = 1; count < gameObserver.getTime(); count++) {
			clock.displayTime(Clock.minutesList.get(count), Clock.secondsList.get(count));
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Set positions of components from saved states 
			board.ball.setX(Board.ballListX.get(count));
			board.ball.setY(Board.ballListY.get(count));
			board.paddle.setX(Board.paddleListX.get(count));
			TemporaryBrick temp = Board.brickList.get(count);
			for(int i=0; i<30; i++){
				board.bricks[i].setDestroyed(temp.tempBrick[i]);
			}
			board.printBoardReplay();
			
			//clock.displayTime(count, count);
		}
	}
}
