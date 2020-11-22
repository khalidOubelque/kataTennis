package kata.game.tennis;

public enum EnumTennisGameStatus {

	DEUCE("deuce"), ADVANTAGE("advantage");

	private String label;

	public String getLabel() {
		return label;
	}

	private EnumTennisGameStatus(String label) {
		this.label = label;
	}

	public static EnumTennisGameStatus getScoreFromValue(String label) {
		for (EnumTennisGameStatus e : EnumTennisGameStatus.values()) {
			if (e.label == label) {
				return e;
			}
		}
		return null;// not found
	}
}
