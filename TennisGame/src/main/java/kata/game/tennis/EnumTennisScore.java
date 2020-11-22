package kata.game.tennis;

public enum EnumTennisScore {
    ZERO(0, "zero"), FIFTEEN(15, "fifteen"), THIRTY(30, "thirty"), FORTY(40, "forty"), ADVANTAGE(50, "advantage"),DEUCE(45, "deuce"), GAME_WIN(1, "game win");

    private int scoreValue;
	

	public int getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	private String name;

	EnumTennisScore(int scoreValue, String name) {
		this.scoreValue = scoreValue;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static String getScoreName(int scoreValue) {
		for (EnumTennisScore EnumScore : EnumTennisScore.values()) {
			if (EnumScore.scoreValue == scoreValue) {
				return EnumScore.name;
			}
//			if (score > 4) {
//				return ADVANTAGE.name;
//			}
		}

		throw new UnsupportedOperationException();
	}
}