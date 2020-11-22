package kata.game.tennis;

public enum EnumMatchStatus {

	IN_PROGRESS("in progress"), PLAYER_WINS("wins");

	private String label;

	public String getLabel() {
		return label;
	}

	private EnumMatchStatus(String label) {
		this.label = label;
	}

	public static EnumMatchStatus getScoreFromValue(String label) {
		for (EnumMatchStatus e : EnumMatchStatus.values()) {
			if (e.label == label) {
				return e;
			}
		}
		return null;// not found
	}
}
