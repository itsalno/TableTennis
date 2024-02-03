public class ScoreManager  
{
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static int playerHit = 0;
    
    public static int getPlayerHit() {
        return playerHit;
    }
    
    public static int getPlayerScore() {
        return playerScore;
    }
    
    public static int getComputerScore() {
        return computerScore;
    }
    
    public static void setPlayerScore(int newPlayerScore) {
        playerScore = newPlayerScore;
    }
    
    public static void setComputerScore(int newComputerScore) {
        computerScore = newComputerScore;
    }
    
    public static void setPlayerHit(int newPlayerHit) {
        playerHit = newPlayerHit;
    }
    
    public static void increasePlayerHitByOne() {
        playerHit++;
    }
    
    public static void increasePlayerScoreByOne() {
        playerScore++;
    }
    
    public static void increaseComputerScoreByOne() {
        computerScore++;
    }
}
