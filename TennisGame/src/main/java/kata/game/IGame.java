package kata.game;

import kata.game.tennis.Player;

public interface IGame {

	public void initScore();
	public String getCurrentGameStatus();
	public void scores(Player playerScores);

}
