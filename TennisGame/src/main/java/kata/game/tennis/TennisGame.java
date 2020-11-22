package kata.game.tennis;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import kata.game.IGame;
import kata.game.IGameSet;


/**
* The TennisGame class defines tennis game played by
* two Players
*
* @author  khalid oubelque
* @version 1.0
* @since   2020-11-22 
*/

public class TennisGame implements IGame {

	private Player playerOne;
	private Player playerTwo;
	private IGameSet tennisSet;
	private static final String CUURENT_GAME_LABEL = "Current game status :";
	private static Logger logger = Logger.getLogger(TennisGame.class.getName());	 

	public TennisGame(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}

	public IGameSet getTennisSet() {
		return tennisSet;
	}

	public void setTennisSet(IGameSet tennisSet) {
		this.tennisSet = tennisSet;
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
	
	public String getPlayerOneNameLabel() {
		return "Player  1 : "+playerOne.getName();
	}
	
	public String getPlayerTwoNameLabel() {
		return "Player  2 : "+playerTwo.getName();
	}
	
	@Override
	public void initScore() {
		playerOne.setScore(EnumTennisScore.ZERO);
		playerTwo.setScore(EnumTennisScore.ZERO);
	}
	
	@Override
	public String getCurrentGameStatus() {
		StringBuilder str = new StringBuilder();
		if (isDeuce()) {
			str.append(CUURENT_GAME_LABEL);
			str.append(" ");
			str.append(EnumTennisGameStatus.DEUCE.getLabel());
			return str.toString();
		} else if (playerOne.hasAdvantage() || playerTwo.hasAdvantage()) {
			str.append(CUURENT_GAME_LABEL);
			str.append(" ");
			str.append(EnumTennisGameStatus.ADVANTAGE.getLabel());
			return str.toString();
		} else if (playerOne.hasWon() || playerTwo.hasWon()) {
			return StringUtils.EMPTY;
		} else {
			str.append(CUURENT_GAME_LABEL);
			str.append(" ");
			str.append(playerOne.getScore().getScoreValue());
			str.append("-");
			str.append(playerTwo.getScore().getScoreValue());
			return str.toString();
		}
	}

	@Override
	public void scores(Player playerScores) {
		if (playerScores.getScore().equals(EnumTennisScore.GAME_WIN)) {
			logger.info("Initialization of Players scores since Player " + playerScores.getName()+ " has already win the game -Score : " + playerScores.getScore());
			initScore();
		}
		Player otherPlayer = retrieveOtherPlayer(playerScores);
		if (isDeuce()) {
			playerScores.setScore(EnumTennisScore.ADVANTAGE);
		} else if (otherPlayer.hasAdvantage() && playerScores.getScore().equals(EnumTennisScore.FORTY)) {
			otherPlayer.setScore(EnumTennisScore.FORTY);
		} else {
			calculateScoring(playerScores);
		}
	}

	private void calculateScoring(Player playerScores) {
		switch (playerScores.getScore()) {
		case ZERO:
			playerScores.setScore(EnumTennisScore.FIFTEEN);
			break;
		case FIFTEEN:
			playerScores.setScore(EnumTennisScore.THIRTY);
			break;
		case THIRTY:
			playerScores.setScore(EnumTennisScore.FORTY);
			break;
		case FORTY:
			playerScores.setScore(EnumTennisScore.GAME_WIN);
			tennisSet.playerWinSetPoint(playerScores);
			break;
		case ADVANTAGE:
			playerScores.setScore(EnumTennisScore.GAME_WIN);
			tennisSet.playerWinSetPoint(playerScores);
			break;
		default:
			logger.error("Unknown Player Score: " + playerScores.getScore());
			throw new IllegalArgumentException("Unknown Player Score: " + playerScores.getScore());
		}
	}

	private Player retrieveOtherPlayer(Player player) {
		return player == playerOne ? playerTwo : playerOne;
	}

	private boolean isDeuce() {
		return playerOne.getScore().equals(EnumTennisScore.FORTY) && playerTwo.getScore().equals(EnumTennisScore.FORTY);
	}

}
