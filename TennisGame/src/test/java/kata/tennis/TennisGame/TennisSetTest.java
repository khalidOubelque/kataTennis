package kata.tennis.TennisGame;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import kata.game.tennis.Player;
import kata.game.tennis.TennisGame;
import kata.game.tennis.TennisSet;

public class TennisSetTest {

	private TennisSet tennisSet;
	private Player playerOne;
	private Player playerTwo;
	private TennisGame game;

	@Before
	public void before() {
		playerOne = new Player("Khalid OUBELQUE");
		playerTwo = new Player("Rafael Nadal");
		tennisSet = new TennisSet(playerOne, playerTwo);
		game = new TennisGame(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
	}

	@After
	public void after() {
		game.initScore();
		tennisSet.initTennisSetResult();
	}

	public void addPlayerGamePointsToSet(int playerOneSet, int playerTwoSet) {
		for (int i = 0; i < playerOneSet; i++) {
			tennisSet.playerWinSetPoint(playerOne);
		}
		for (int i = 0; i < playerTwoSet; i++) {
			tennisSet.playerWinSetPoint(playerTwo);
		}
	}

	/*
	 * Test Tennis set start with score (0-0)
	 */
	@Test
	public void testTennisSetStartsWithZero() {
		Assert.assertEquals("(0-0)", tennisSet.getScoreSet());
	}

	/*
	 * Test PLayer 1 win set by (6-1)
	 */
	@Test
	public void testPlayerOneWinsSet() {
		addPlayerGamePointsToSet(6, 1);
		Assert.assertEquals("(6-1)", tennisSet.getScoreSet());
	}

	/*
	 * Test PLayer 2 win set by (4-6)
	 */
	@Test
	public void testPlayerTwoWinsSet() {
		addPlayerGamePointsToSet(4, 6);
		Assert.assertEquals("(4-6)", tennisSet.getScoreSet());
	}

	/*
	 * Test if there is a winner in a set (6-5) -> should return false
	 */
	@Test
	public void testNoWinner1() {
		addPlayerGamePointsToSet(6, 5);
		Assert.assertFalse(tennisSet.isWinnerSet());
	}

	/*
	 * Test if there is a winner in a set (6-3) -> should return true
	 */
	@Test
	public void testNoWinner2() {
		addPlayerGamePointsToSet(6, 3);
		Assert.assertTrue(tennisSet.isWinnerSet());
	}

	/*
	 * Test if there is a Tie-break means we have set like (6-6)
	 */
	@Test
	public void testTieBreak() {
		addPlayerGamePointsToSet(6, 6);
		Assert.assertTrue(tennisSet.isTieBreak());
	}

	/*
	 * Test using TennisGame instance
	 * 
	 */

	/*
	 * Used to score multiple times
	 */
	public void multipleScores(Player playerScores, int times) {
		for (int i = 0; i < times; i++) {
			game.scores(playerScores);
		}
	}

	/*
	 * Test Player one losses 0 - 6 to player two
	 */
	@Test
	public void testTennisGame1() {
		multipleScores(playerTwo, 4);
		Assert.assertEquals("(0-1)", tennisSet.getScoreSet());
	}

	/*
	 * Test Player one losses 2 - 6 to player two
	 */
	@Test
	public void testTennisGame2() {
		multipleScores(playerTwo, 4);
		multipleScores(playerTwo, 4);
		multipleScores(playerOne, 4);
		multipleScores(playerTwo, 4);
		multipleScores(playerOne, 4);
		multipleScores(playerTwo, 4);
		multipleScores(playerTwo, 4);
		multipleScores(playerTwo, 4);

		Assert.assertEquals("(2-6)", tennisSet.getScoreSet());
	}

}
