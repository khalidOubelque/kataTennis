package kata.tennis.TennisGame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import kata.game.tennis.EnumTennisGameStatus;
import kata.game.tennis.EnumTennisScore;
import kata.game.tennis.Player;
import kata.game.tennis.TennisGame;
import kata.game.tennis.TennisSet;

public class TennisGameTest {

	private static Player player1;
	private static Player player2;
	private static TennisGame game;
	private static TennisSet tennisSet;
	private static final String CUURENT_GAME_LABEL = "Current game status : ";

	@Before
	public void beforeMethod() {  
		player1 = new Player("Khalid OUBELQUE");
		player2 = new Player("Rafael Nadal");
		game = new TennisGame(player1, player2);
		tennisSet = new TennisSet(player1, player2);
		game.setTennisSet(tennisSet);

	}

	@After
	public void after() {
		initScore();
	}
	
	public void initScore() {
		player1.setScore(EnumTennisScore.ZERO);
		player2.setScore(EnumTennisScore.ZERO);
	}

	@Test
	public void testNewGamePlayersScoreZeroAll() {		
		Assert.assertEquals(EnumTennisScore.ZERO, player1.getScore());
		Assert.assertEquals(EnumTennisScore.ZERO, player2.getScore());
	}

	/*
	 * Test player 1 scores from zero to game win
	 */
	@Test
	public void playerScoresFromZeroToWinPoint() {
		Assert.assertEquals(EnumTennisScore.ZERO, player1.getScore());

		game.scores(player1);
		Assert.assertEquals(EnumTennisScore.FIFTEEN, player1.getScore());

		game.scores(player1);
		Assert.assertEquals(EnumTennisScore.THIRTY, player1.getScore());

		game.scores(player1);
		Assert.assertEquals(EnumTennisScore.FORTY, player1.getScore());

		game.scores(player1);
		Assert.assertEquals(EnumTennisScore.GAME_WIN, player1.getScore());

	}

	// Test player 1 wins but continue scoring on the same game -> Throw IllegalArgumentException
	// No need for this test, it's done at the first step before coding TennisSet class
//	@Test(expected = IllegalArgumentException.class)
//	@Ignore("Not used")
//	void playerWinsContinueScoring() {
//		playerScoresFromZeroToWinPoint();
//
//		Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
//			game.scores(player1);
//		});
//
//		String expectedMessage = "Player " + player1.getName() + " has already win the game -Score : "
//				+ player1.getScore();
//		String actualMessage = exception.getMessage();
//
//		Assert.assertTrue(actualMessage.contains(expectedMessage));
//	}

	/*
	 * Test player 1 scores 15 and player 2 scores 30
	 */
	@Test
	public void testPlayExemple1() {
		game.scores(player1);
		Assert.assertEquals(EnumTennisScore.FIFTEEN, player1.getScore());
		Assert.assertEquals(15, player1.getScore().getScoreValue());

		game.scores(player2);
		Assert.assertEquals(EnumTennisScore.FIFTEEN, player1.getScore());
		Assert.assertEquals(15, player1.getScore().getScoreValue());

		game.scores(player2);
		Assert.assertEquals(EnumTennisScore.THIRTY, player2.getScore());
		Assert.assertEquals(30, player2.getScore().getScoreValue());
	}

	@Test
	public void testPlayerOneWinsAfterAdvantageandDeuce() {
		game.scores(player1);
		game.scores(player2);
		game.scores(player2);
		game.scores(player1);
		game.scores(player2);
		game.scores(player1);
		game.scores(player1);// player one gain advantage over player two

		String expected = CUURENT_GAME_LABEL + EnumTennisGameStatus.ADVANTAGE.getLabel();
		Assert.assertEquals(expected, game.getCurrentGameStatus());
		Assert.assertEquals(EnumTennisScore.ADVANTAGE, player1.getScore());

		game.scores(player2); // player one lost advantage over player two
		Assert.assertEquals(EnumTennisScore.FORTY, player1.getScore());
		Assert.assertEquals(EnumTennisScore.FORTY, player2.getScore());

		game.scores(player1);
		game.scores(player1);
		Assert.assertEquals(EnumTennisScore.GAME_WIN, player1.getScore());
	}

	
	/*
	 * Test current game status -> 15-30
	 */
	@Test
	public void testCurrentGameExemple1() {
		//beforeMethod();
		game.scores(player1);
		game.scores(player2);
		game.scores(player2);
		String expected = CUURENT_GAME_LABEL + "15-30";
		Assert.assertEquals(expected, game.getCurrentGameStatus());
	}

	/*
	 * Test current game status -> deuce
	 */
	@Test
	public void testCurrentGameExemple2() {
		game.scores(player1);
		game.scores(player2);
		game.scores(player2);
		game.scores(player1);
		game.scores(player1);
		game.scores(player2);

		String expected = CUURENT_GAME_LABEL + EnumTennisGameStatus.DEUCE.getLabel();
		Assert.assertEquals(expected, game.getCurrentGameStatus());
	}

	/*
	 *  Test current game status -> advantage for player one
	 */
	@Test
	public void testCurrentGameExemple3() {
		game.scores(player1);
		game.scores(player2);
		game.scores(player2);
		game.scores(player1);
		game.scores(player1);
		game.scores(player2);
		game.scores(player1);

		String expected = CUURENT_GAME_LABEL + EnumTennisGameStatus.ADVANTAGE.getLabel();
		Assert.assertEquals(expected, game.getCurrentGameStatus());
	}
	
	@AfterClass
	public static void cleanUp() {
	   //System.out.println("After All cleanUp() method called");
	}
	
}
