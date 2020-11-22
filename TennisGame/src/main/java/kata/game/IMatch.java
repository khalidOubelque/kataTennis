package kata.game;

import java.util.Optional;

import kata.game.tennis.Player;

public interface IMatch {

	public Optional<Player> getMatchWinner();

}
