import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_Player1Scores() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		
		String score =game.getScore();
		
		assertEquals("Incorrect score", "15 - love", score);
	}
	
	@Test
	public void testTennisGame_Player2Scores() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Incorrect score", "love - 15", score);
	}
	
	@Test
	public void testTennisGame_Player1Score30() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Incorrect score", "30 - love", score);
	}
	
	@Test
	public void testTennisGame_Player1Scores40() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Incorrect score", "40 - love", score);
	}
	
	@Test
	public void testTennisGame_Player1WinsWithoutAdvantage() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Incorrect score", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2WinsWithoutAdvantage() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Incorrect score", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_Player1GainsAnAdvantage() throws TennisGameException{
		
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();		
		game.player1Scored();
		
		String score = game.getScore();	
		
		assertEquals("Advantage incorrect", "player1 has advantage", score);
	}
	
	@Test
	public void testTennisGame_Player2GainsAnAdvantage() throws TennisGameException{
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();		
		game.player2Scored();
		
		String score = game.getScore();	
		
		assertEquals("Advantage incorrect", "player2 has advantage", score);
	}
	
	@Test
	public void testTennisGame_Player1WinsAfterAdvantage() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();		
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();	
		
		assertEquals("Advantage incorrect", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2WinsAfterAdvantage() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();		
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();	
		
		assertEquals("Advantage incorrect", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();
		
	}
	
	
	
	
	
}
