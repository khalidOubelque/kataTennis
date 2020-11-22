package kata.game.tennis;

import java.util.Optional;

import org.apache.log4j.Logger;
import kata.game.IGameSet;

/**
* The TennisSet defines set of tennis games played by
* two Players
*
* @author  khalid oubelque
* @version 1.0
* @since   2020-11-22 
*/

public class TennisSet implements IGameSet {
	
	private static final int SET_COUNT = 1;
	private static final int SET_PLAYERS_COUNT = 2;// can be four players
	public static final int TENNIS_DIFFERENCE = 2;
	private int[][] tennisSetResult;
	private Player playerOne;
	private Player playerTwo;
	private static Logger logger = Logger.getLogger(TennisSet.class.getName());	 

	public TennisSet(Player playerOne, Player playerTwo) {
		tennisSetResult = new int[SET_COUNT][SET_PLAYERS_COUNT];
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}
	
	public void initTennisSetResult() {
		for (int i = 0; i < SET_COUNT; i++) {
			for (int j = 0; j < SET_PLAYERS_COUNT; j++) {
				tennisSetResult[i][j] = 0;
			}
		}
	}

	/**
	* This function increments player points after finishing a game, giving player. 
	* @param  player  
	*/
	public void playerWinSetPoint(Player playerWinsGame) {
		if(playerWinsGame.equals(playerOne)) {
			tennisSetResult[0][0] = tennisSetResult[0][0] + 1;
		} else if(playerWinsGame.equals(playerTwo)) {
			tennisSetResult[0][1] = tennisSetResult[0][1] + 1;
		}else {
			throw new IllegalArgumentException("Unknown Player : " + playerWinsGame.getName());
		}
	}

	/**
	* This function returns score of a set. 
	* @return      the score of a set as (X-Y)
	*/
	@Override
	public String getScoreSet() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < SET_COUNT; i++) {
			str.append("(");
			for (int j = 0; j < SET_PLAYERS_COUNT; j++) {
				str.append(tennisSetResult[i][j]);
				if(j != SET_PLAYERS_COUNT-1) {
					str.append("-");
				}
			}
			str.append(")");
		}
		return str.toString();
	}

	/**
	* This function checks if there is a winner of a set. 
	* @return      True if there is a winner and False if not.
	*/
	@Override
	public boolean isWinnerSet() {
		int playerOneSetGamePoints = tennisSetResult[0][0];
		int playerTwoSetGamePoints = tennisSetResult[0][1];
        boolean isPlayerOneWon =  playerOneSetGamePoints >= 6 && playerOneSetGamePoints >= playerTwoSetGamePoints + TENNIS_DIFFERENCE;
        boolean isPlayerTwoWon =  playerTwoSetGamePoints >= 6 && playerTwoSetGamePoints >= playerOneSetGamePoints + TENNIS_DIFFERENCE;
        boolean isPlayerWonTieBreak  =  playerTwoSetGamePoints == 7 || playerTwoSetGamePoints == 7;

        return isPlayerOneWon || isPlayerTwoWon || isPlayerWonTieBreak;
	}
	
	/**
	* This function checks TieBreak situation in a set
	* @return      True if yes and False if not.
	*/
	public boolean isTieBreak() {
		int playerOneSetGamePoints = tennisSetResult[0][0];
		int playerTwoSetGamePoints = tennisSetResult[0][1];
        return playerOneSetGamePoints== 6 && playerTwoSetGamePoints == 6;
	}

	/**
	* This function gets The winner player given a set
	* @return      Optional<Player> if exist, Optional.empty() if not.
	*/
	public Optional<Player> getPlayerSetWinner() {
		if(isWinnerSet()) {
			int playerOneSetGamePoints = tennisSetResult[0][0];
			int playerTwoSetGamePoints = tennisSetResult[0][1];
			if (playerOneSetGamePoints > playerTwoSetGamePoints) {
				return Optional.of(playerOne);
			} else {
				return Optional.of(playerTwo);
			}
		}else {
			logger.error("Game still have not finished: "+getScoreSet());
			return Optional.empty();
		}
 
	}
}
