package kata.tennis.TennisGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kata.game.IGameSet;
import kata.game.tennis.Player;
import kata.game.tennis.TennisGame;
import kata.game.tennis.TennisMatch;
import kata.game.tennis.TennisSet;

public class TennisMatchSet {

	private TennisMatch tennisMatch;
	private TennisSet tennisSet;
	private Player playerOne;
	private Player playerTwo;
	private TennisGame game;
	private static final String CUURENT_GAME_LABEL = "Current game status : ";

	@Before
	public void before() {
		playerOne = new Player("Khalid OUBELQUE");
		playerTwo = new Player("Rafael Nadal");
		tennisMatch = new TennisMatch(playerOne, playerTwo);
		tennisSet = new TennisSet(playerOne, playerTwo);
		tennisMatch.setListTennisSets(Arrays.asList(tennisSet));
		game = new TennisGame(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
	}

	@After
	public void after() {
		game.initScore();
		tennisSet.initTennisSetResult();
		tennisMatch.initTennisSets();
	}
	
	/*
	 * Test match status where match sets is (6-4) (6-4) (6-4) for player One
	 */
	@Test
	public void testMatchStatus() {
		String excpectedResult = "Match Status : Khalid OUBELQUE wins";
		List<IGameSet> listTennisMatchSet = creatsStaticListMatchTennisSet();
		tennisMatch.setListTennisSets(listTennisMatchSet);
		Assert.assertEquals(excpectedResult, tennisMatch.getMatchStatus());
	}
	
	/*
	 * Test get match winner where match sets is (6-4) (6-4) (6-4) for player One
	 */
	@Test
	public void testgetMatchWinner() {
		List<IGameSet> listTennisMatchSet = creatsStaticListMatchTennisSet();
		tennisMatch.setListTennisSets(listTennisMatchSet);
		Assert.assertEquals(tennisMatch.getPlayerOne(), tennisMatch.getMatchWinner().get());
	}
	
	/*
	 * Test get current match score where match sets is (6-4) (6-4) (6-4) for player One
	 */
	@Test
	public void testgetMatchScore() {
		StringBuilder str = new StringBuilder();
		str.append("Score : ");
		str.append("(6-4) (6-4) (6-4) ");
		List<IGameSet> listTennisMatchSet = creatsStaticListMatchTennisSet();
		tennisMatch.setListTennisSets(listTennisMatchSet);
		Assert.assertEquals(str.toString(), tennisMatch.getScoreSets());
	}
	
	/*
	 * Create match sets where player 1 wins (6-4) (6-4) (6-4)
	 */
	public List<IGameSet> creatsStaticListMatchTennisSet(){
		List<IGameSet> listTennisMatchSet = new ArrayList<>();
		for (int i = 0; i<3; i++) {
			TennisSet set = new TennisSet(playerOne, playerTwo);
			addPlayerGamePointsToSet(6, 4, set);
			listTennisMatchSet.add(set);
		}
		return listTennisMatchSet;
	}
	
	public IGameSet creatsStaticListMatchTennisSet(int playerOneSet, int playerTwoSet){
		TennisSet set = new TennisSet(playerOne, playerTwo);
		addPlayerGamePointsToSet(playerOneSet, playerTwoSet, set);
		return set;
	}
	
	/*
	 * Test Exemple 1 match game "(6-1) (7-5) (1-0)" and match status "in progress"
	 *  Player  1 :  nom du joueur1
		Player  2 : nom du joueur2
		Score : (6-1) (7-5) (1-0)
		Current game status : 15-30
		Match Status : in progress
	 */
	@Test
	public void testExemple1() {
		StringBuilder score = new StringBuilder();
		score.append("Score : ");
		score.append("(6-1) (7-5) (1-0) ");
		String matchStatus = "Match Status : in progress ";
			
		TennisGame game = new TennisGame(playerOne, playerTwo);
		TennisSet tennisSet1 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(6, 1, tennisSet1);
		TennisSet tennisSet2 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(7, 5, tennisSet2);
		TennisSet tennisSet3 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(1, 0, tennisSet3);
		
		// Player 1 name
		String playerOneLabel = "Player  1 : "+playerOne.getName();
		Assert.assertEquals(playerOneLabel, game.getPlayerOneNameLabel());
				
		// Player 2 name
		String playerTwoLabel = "Player  2 : "+playerTwo.getName();
		Assert.assertEquals(playerTwoLabel, game.getPlayerTwoNameLabel());
				
		List<IGameSet> listTennisMatchSet = new ArrayList<>(); 
		listTennisMatchSet.add(tennisSet1);
		listTennisMatchSet.add(tennisSet2);
		listTennisMatchSet.add(tennisSet3);
		tennisMatch.setListTennisSets(listTennisMatchSet);
		
		//  score
		Assert.assertEquals(score.toString(), tennisMatch.getScoreSets());
			
		// Current Game score
		game.scores(playerOne);
		game.scores(playerTwo);
		game.scores(playerTwo);
		String currentGame = CUURENT_GAME_LABEL + "15-30";
		Assert.assertEquals(currentGame, game.getCurrentGameStatus());
		
		
		// Match status
		Assert.assertEquals(matchStatus, tennisMatch.getMatchStatus());
		
	}
	
	/*
	 * Exemple 2
		Player  1 :  nom du joueur1
		Player  2 : nom du joueur2
		Score : (6 -1) (7-5) (0-0)
		Current game status : deuce
		Match Status : in progress
	 * 
	 */
	@Test
	public void testExemple2() {
		StringBuilder score = new StringBuilder();
		score.append("Score : ");
		score.append("(6-1) (7-5) (0-0) ");
		String matchStatus = "Match Status : in progress ";
			
		TennisGame game = new TennisGame(playerOne, playerTwo);
		TennisSet tennisSet1 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(6, 1, tennisSet1);
		TennisSet tennisSet2 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(7, 5, tennisSet2);
		TennisSet tennisSet3 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(0, 0, tennisSet3);
		
		// Player 1 name
		String playerOneLabel = "Player  1 : "+playerOne.getName();
		Assert.assertEquals(playerOneLabel, game.getPlayerOneNameLabel());
				
		// Player 2 name
		String playerTwoLabel = "Player  2 : "+playerTwo.getName();
		Assert.assertEquals(playerTwoLabel, game.getPlayerTwoNameLabel());
				
		List<IGameSet> listTennisMatchSet = new ArrayList<>(); 
		listTennisMatchSet.add(tennisSet1);
		listTennisMatchSet.add(tennisSet2);
		listTennisMatchSet.add(tennisSet3);
		tennisMatch.setListTennisSets(listTennisMatchSet);
		
		//  score
		Assert.assertEquals(score.toString(), tennisMatch.getScoreSets());
			
		// Current Game score
		game.scores(playerOne);
		game.scores(playerOne);
		game.scores(playerOne);

		game.scores(playerTwo);
		game.scores(playerTwo);
		game.scores(playerTwo);
		String currentGame = CUURENT_GAME_LABEL + "deuce";
		Assert.assertEquals(currentGame, game.getCurrentGameStatus());
		
		
		// Match status
		Assert.assertEquals(matchStatus, tennisMatch.getMatchStatus());
	}
	
	/*
	 * Exemple 3
		Player  1 :  nom du joueur1
		Player  2 : nom du joueur2
		Score : (6 -1) (7-5) (0-0)
		Current game status : advantage
		Match Status : in progress
	 * 
	 */
	@Test
	public void testExemple3() {
		StringBuilder score = new StringBuilder();
		score.append("Score : ");
		score.append("(6-1) (7-5) (0-0) ");
		String matchStatus = "Match Status : in progress ";
			
		TennisGame game = new TennisGame(playerOne, playerTwo);
		TennisSet tennisSet1 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(6, 1, tennisSet1);
		TennisSet tennisSet2 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(7, 5, tennisSet2);
		TennisSet tennisSet3 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(0, 0, tennisSet3);
		
		// Player 1 name
		String playerOneLabel = "Player  1 : "+playerOne.getName();
		Assert.assertEquals(playerOneLabel, game.getPlayerOneNameLabel());
				
		// Player 2 name
		String playerTwoLabel = "Player  2 : "+playerTwo.getName();
		Assert.assertEquals(playerTwoLabel, game.getPlayerTwoNameLabel());
				
		List<IGameSet> listTennisMatchSet = new ArrayList<>(); 
		listTennisMatchSet.add(tennisSet1);
		listTennisMatchSet.add(tennisSet2);
		listTennisMatchSet.add(tennisSet3);
		tennisMatch.setListTennisSets(listTennisMatchSet);
		
		//  score
		Assert.assertEquals(score.toString(), tennisMatch.getScoreSets());
			
		// Current Game score
		game.scores(playerOne);
		game.scores(playerOne);
		game.scores(playerOne);

		game.scores(playerTwo);
		game.scores(playerTwo);
		game.scores(playerTwo);
		game.scores(playerOne);

		String currentGame = CUURENT_GAME_LABEL + "advantage";
		Assert.assertEquals(currentGame, game.getCurrentGameStatus());
		
		
		// Match status
		Assert.assertEquals(matchStatus, tennisMatch.getMatchStatus());
	}
	
	/*
	 *Exemple 4
		Player 1 :  nom du joueur1
		Player 2 : nom du joueur2
		Score : (6 -1) (7-5) (6-0)
		Match Status : Player1 wins
	 * 
	 */
	@Test
	public void testExemple4() {
		StringBuilder score = new StringBuilder();
		score.append("Score : ");
		score.append("(6-1) (7-5) (6-0) ");
		String matchStatus = "Match Status : Khalid OUBELQUE wins";
			
		TennisGame game = new TennisGame(playerOne, playerTwo);
		TennisSet tennisSet1 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(6, 1, tennisSet1);
		TennisSet tennisSet2 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(7, 5, tennisSet2);
		TennisSet tennisSet3 = new TennisSet(playerOne, playerTwo);
		game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(6, 0, tennisSet3);
		
		// Player 1 name
		String playerOneLabel = "Player  1 : "+playerOne.getName();
		Assert.assertEquals(playerOneLabel, game.getPlayerOneNameLabel());
				
		// Player 2 name
		String playerTwoLabel = "Player  2 : "+playerTwo.getName();
		Assert.assertEquals(playerTwoLabel, game.getPlayerTwoNameLabel());
				
		List<IGameSet> listTennisMatchSet = new ArrayList<>(); 
		listTennisMatchSet.add(tennisSet1);
		listTennisMatchSet.add(tennisSet2);
		listTennisMatchSet.add(tennisSet3);
		tennisMatch.setListTennisSets(listTennisMatchSet);
		
		//  score
		Assert.assertEquals(score.toString(), tennisMatch.getScoreSets());
		
		// Match status
		Assert.assertEquals(matchStatus, tennisMatch.getMatchStatus());
	}
	
	/*
	 Exemple 5
	Player 1 :  nom du joueur1
	Player 2 : nom du joueur2
	Score : (6 -1) (7-5) (2-6) (6-7) (4-6)
	Match Status : Player 2 wins
	 * 
	 */
	@Test
	public void testExemple5() {
		StringBuilder score = new StringBuilder();
		score.append("Score : ");
		score.append("(6-1) (7-5) (2-6) (6-7) (4-6) ");
		String matchStatus = "Match Status : Rafael Nadal wins";
			
		TennisGame game = new TennisGame(playerOne, playerTwo);
		TennisSet tennisSet1 = new TennisSet(playerOne, playerTwo);
		//game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(6, 1, tennisSet1);
		TennisSet tennisSet2 = new TennisSet(playerOne, playerTwo);
		//game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(7, 5, tennisSet2);
		TennisSet tennisSet3 = new TennisSet(playerOne, playerTwo);
		//game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(2, 6, tennisSet3);
		
		TennisSet tennisSet4 = new TennisSet(playerOne, playerTwo);
		//game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(6, 7, tennisSet4);
		
		TennisSet tennisSet5 = new TennisSet(playerOne, playerTwo);
		//game.setTennisSet(tennisSet);
		addPlayerGamePointsToSet(4, 6, tennisSet5);
		
		// Player 1 name
		game.setTennisSet(tennisSet);
		String playerOneLabel = "Player  1 : "+playerOne.getName();
		Assert.assertEquals(playerOneLabel, game.getPlayerOneNameLabel());
				
		// Player 2 name
		String playerTwoLabel = "Player  2 : "+playerTwo.getName();
		Assert.assertEquals(playerTwoLabel, game.getPlayerTwoNameLabel());
				
		List<IGameSet> listTennisMatchSet = new ArrayList<>(); 
		listTennisMatchSet.add(tennisSet1);
		listTennisMatchSet.add(tennisSet2);
		listTennisMatchSet.add(tennisSet3);		
		listTennisMatchSet.add(tennisSet4);
		listTennisMatchSet.add(tennisSet5);

		tennisMatch.setListTennisSets(listTennisMatchSet);
		
		//  score
		Assert.assertEquals(score.toString(), tennisMatch.getScoreSets());
		
		// Match status
		Assert.assertEquals(matchStatus, tennisMatch.getMatchStatus());
	}
	
	public void addPlayerGamePointsToSet(int playerOneSet, int playerTwoSet, TennisSet set) {
		for (int i = 0; i < playerOneSet; i++) {
			set.playerWinSetPoint(set.getPlayerOne());
		}
		for (int i = 0; i < playerTwoSet; i++) {
			set.playerWinSetPoint(set.getPlayerTwo());
		}
	}
	
}
