package kata.game;

import java.util.Optional;

import kata.game.tennis.Player;

public interface IGameSet {

	public String getScoreSet();
	public boolean isWinnerSet();
	public Optional<Player> getPlayerSetWinner();
	public void playerWinSetPoint(Player playerWinsGame) ;


}
