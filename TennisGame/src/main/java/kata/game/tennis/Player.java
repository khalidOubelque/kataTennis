package kata.game.tennis;

import org.apache.commons.lang3.StringUtils;

public class Player {

	private String name;
	private static final String defaultName = "DefaultPlayer";
	private EnumTennisScore score;

	public void setScore(EnumTennisScore score) {
		this.score = score;
	}

	public Player(String name) {
		if (StringUtils.isEmpty(name)) {
			name = defaultName;
		}
		this.name = name;
		this.score = EnumTennisScore.ZERO;
	}

	public EnumTennisScore getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
	
	public boolean hasAdvantage() {
		return score.equals(EnumTennisScore.ADVANTAGE);
	}
	
	public boolean hasWon() {
		return score.equals(EnumTennisScore.GAME_WIN);
	}
}
