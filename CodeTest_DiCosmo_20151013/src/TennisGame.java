import java.util.Random;

/**
 * @author Di Cosmo
 * 
 * This class test a tennis game
 * Rules:
 * Scores from zero to three points are described as �love�, �fifteen�, �thirty�, and �forty� respectively.
 * If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is �advantage� for the player in the lead.
 * If at least three points have been scored by each player, and the scores are equal, the score is �deuce�.
 * A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
 */
public class TennisGame {

	public static void main(String[] args){
		
		// create two player variable
		Player player1 = new Player();
		Player player2 = new Player();
		// initialize endGame and player points
		int endGame = 0;
		int pointP1 = player1.getPoint();
		int pointP2 = player2.getPoint();
		String lsScore = "";
		
		player1.setNamePlayer("Djokovic");
		player2.setNamePlayer("Federer");
		
		System.out.println("Player : \t" + player1.getNamePlayer() + " - " + player2.getNamePlayer() );
		
		// while the game is not end, call simulation player point
		while (endGame != 1){
			// get simulation game point
			int point = getPlayerPoint();
			if (point == 1){
				pointP1 = pointP1+1;
			}else{
				pointP2 = pointP2+1;
			}
			// set the point
			player1.setPoint(pointP1);
			player2.setPoint(pointP2);
			
			// get result game
			endGame = getResultGame(player1, player2);
						
			System.out.print("Score : (" + pointP1 + " - " + pointP2 + ") ");
			if (endGame == 0){

				lsScore =  getScore(pointP1) + " - " + getScore(pointP2);
				System.out.println(lsScore);
			}
			else if (endGame == 1){
				if (player1.getWinner() == 1){
					System.out.println(player1.getNamePlayer() +" won the game");
				}else{
					System.out.println(player2.getNamePlayer() +" won the game");
				}
				
			}else if (endGame == -1){
				
				if (player1.getAdvantage() == 1){
					System.out.println("Advantage for " + player1.getNamePlayer());
				}else{
					System.out.println("Advantage for " + player2.getNamePlayer());
				}
			}else if (endGame == -2){
				System.out.println("Deuce");
			}
		}
	}
	
	/**
	 * This function get point's description
	 * @param point the point are described as:
	 * 		0 love
	 * 		1 fifteen
	 * 		2 thirty
	 * 		3 forty
	 * 	   -1 advantage
	 * 	   -2 deuce
	 * @return point described
	 */
	public static String getScore(int point){
		String score = "";
		if (point == 0){
			score = "love";
		}else if(point == 1){
			score ="fifteen";
		}else if(point == 2){
			score = "thirty";
		}else if(point == 3){
			score = "forty";
		}else if(point == -1){
			score = "advantage";
		}else if(point == -2){
			score = "deuce";
		}
		
		return score;
	}
	
	/**
	 * This function return score
	 * @return 0 the game continue
	 * 		   1 the game is end
	 * 		  -1 advantage
	 * 		  -2 deuce			
	 */
	public static int getResultGame(Player player1, Player player2){
		int ret = 0;
		int pointP1 = player1.getPoint();
		int pointP2 = player2.getPoint();
		if (pointP1 > 3 || pointP2 > 3){
			if ((pointP1 - pointP2) >= 2){
				player1.setWinner(1);
				ret = 1;
			}else if ((pointP2 - pointP1) >= 2){
				player2.setWinner(1);
				ret = 1;
			}else if (pointP1 == pointP2){
				player1.setAdvantage(0);
				player2.setAdvantage(0);
				ret = -2;
			}else if (pointP1 > pointP2){
				player1.setAdvantage(1);
				player2.setAdvantage(0);
				ret = -1;
			}else if (pointP2 > pointP1){
				player1.setAdvantage(0);
				player2.setAdvantage(1);
				ret = -1;
			}				
		}else if (pointP1 == pointP2 && pointP1 == 3){
			player1.setAdvantage(0);
			player2.setAdvantage(0);
			ret = -2;
		}
		
		return ret;
	}
	/*
	 * This function get a simulation player point
	 */
	public static int getPlayerPoint(){
		Random random = new Random();
		int n = 2;
		int point = random.nextInt(n);
		return point;
	}
	
	
}
