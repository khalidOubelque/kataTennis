package kata.game.tennis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import kata.game.IGameSet;
import kata.game.IMatch;

public class TennisMatch implements IMatch{

	private List<IGameSet> listTennisSets;
	private Player playerOne;
	private Player playerTwo;
	private int nbWinsForPlayerOne = 0;
	private int nbWinsForPlayerTwo = 0;
	
	public TennisMatch(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.listTennisSets = new ArrayList<>();
		listTennisSets.add(new TennisSet(playerOne, playerTwo));
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public List<IGameSet> getListTennisSets() {
		return listTennisSets;
	}

	public void setListTennisSets(List<IGameSet> listTennisSets) {
		this.listTennisSets = listTennisSets;
	}

	public void initTennisSets() {
		listTennisSets.clear();
	}
	
	public Optional<Player>  getMatchWinner() {
		if(isMatchFinished()) {
			return nbWinsForPlayerOne == 3 ? Optional.of(playerOne) : Optional.of(playerTwo);
		}
		return Optional.empty();
	}
	
	public boolean isMatchFinished() {
		List<IGameSet> listSetWinForPlayerOne = listTennisSets.stream().filter(p -> p.getPlayerSetWinner().isPresent() && p.getPlayerSetWinner().get().equals(playerOne)).
				collect(Collectors.toList());
		
		List<IGameSet> listSetWinForPlayerTwo = listTennisSets.stream().filter(p -> p.getPlayerSetWinner().isPresent() && p.getPlayerSetWinner().get().equals(playerTwo)).
				collect(Collectors.toList());
		nbWinsForPlayerOne = listSetWinForPlayerOne.size();
		nbWinsForPlayerTwo = listSetWinForPlayerTwo.size();
		
		//To win the match, a player must win 3 sets.
		if(nbWinsForPlayerOne == 3 || nbWinsForPlayerTwo == 3) {
			return true;
		}
		return false;
	}
	
	public String getMatchStatus() {
		StringBuilder str = new StringBuilder();
		str.append("Match Status : ");
		if(isMatchFinished()) {
			Optional<Player>  winnerPlayer = getMatchWinner();
			str.append(winnerPlayer.get().getName());
			str.append(" ");
			str.append(EnumMatchStatus.PLAYER_WINS.getLabel());
			return str.toString();
		}else {
			str.append(EnumMatchStatus.IN_PROGRESS.getLabel());
			str.append(" ");
			return str.toString();
		}
	}
	
	public String getScoreSets() {
		StringBuilder str = new StringBuilder();
		str.append("Score : ");
		listTennisSets.forEach((p) -> {str.append(p.getScoreSet());
										str.append(" ");
		});
		return str.toString();
	}
	
	
}
