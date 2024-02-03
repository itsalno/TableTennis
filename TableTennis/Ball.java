import greenfoot.*;

public class Ball extends Actor
{    
    private boolean reset = true;
    
    private int moveSpeed = 4;
    private int hitCount = 0;
    private int level = 1;
    private int size = 25;
    
    private Paddle lastHitPaddle = null;
    private boolean playerPaddleLastHit = false;
    private int lastPosY;
    private int deadBallCounter = 0;
    private int deadBallThreshold = 100;
    private boolean canScorePoint = true;
    
    public Ball() {
        getImage().scale(size, size);
    }
    
    private void playSound(String type, int volume) {
        String sound = "";
        
        switch(type) {
            case "collision": 
                sound = "PP_Cute_Impact_1_4.wav";
                break;
            case "score-player": 
                sound = "Right 1.mp3";
                break;
            case "score-computer": 
                sound = "Wrong 1.mp3";
                break;
            default:
                return;
        }
        
        GreenfootSound soundToPlay = new GreenfootSound(sound);
        soundToPlay.setVolume(volume);
        soundToPlay.play();
    }
    
    private void deadBallCheck() {
        if (lastPosY == getY()) {
            deadBallCounter++;
            
            if (deadBallCounter > deadBallThreshold) {
                resetBall();
                deadBallCounter = 0;
            }
        }
        
        lastPosY = getY();
    }
    
    private String returnWorldEdgeCollision() {
        if (getX() <= size/2) {
            return "left";
        } else if (getX() >= getWorld().getWidth() - size/2) {
            return "right";
        } else if (getY() <= size/2) {
            return "top";
        } else if (getY() >= getWorld().getHeight() - size/2) {
             return "bottom";
        }
        
        return null;
    }
    
    private void worldCollision() {
        String collisionEdge = returnWorldEdgeCollision();
            
        if (collisionEdge == "top" && canScorePoint) {
            scoredPoint(true);
            reflectVertical();
        } else if (collisionEdge == "bottom" && canScorePoint) {
            scoredPoint(false);
            reflectVertical();
        } else if (collisionEdge == "left" || collisionEdge == "right") {
            reflectHorizontal();
        } else {
            canScorePoint = true;
        }
    }
    
    public void act()
    {
        if (!reset) {
            move(moveSpeed + level);
            
            worldCollision();
            checkPaddleCollision();
            deadBallCheck();
        } else if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) {
            playBall();
        }
    }
    
    public boolean wasPlayerPaddleLastHit() {
        return playerPaddleLastHit;
    }
    
    private void endGameWithWinner(boolean playerWon) {
        Greenfoot.setWorld(new GameOverWorld(playerWon));
        ScoreManager.setPlayerHit(0);
        getWorldOfType(TableTennisWorld.class).stopMusic();
    }
    
    private void scoredPoint(boolean playerScored) {
        if (playerScored) {
            ScoreManager.increasePlayerHitByOne();
            playSound("score-player", 20);
            lastHitPaddle = null;
            playerPaddleLastHit = false;
            canScorePoint = false;
            
            if (ScoreManager.getPlayerHit() >= 3) {
                ScoreManager.increasePlayerScoreByOne();
                endGameWithWinner(true);
            }
        } else {
            ScoreManager.increaseComputerScoreByOne();
            playSound("score-computer", 30);
            endGameWithWinner(false);
        }
    }
    
    public void resetBall() {
        setLocation(getWorld().getWidth()/2, getWorld().getHeight()/2);
        setRotation(Greenfoot.getRandomNumber(40) + 70);
        lastHitPaddle = null;
        playerPaddleLastHit = false;
        reset = true;
    }
    
    private void playBall() {
        reset = false;
    }
        
    private void checkPaddleCollision() {
        Paddle paddle = (Paddle)getOneIntersectingObject(Paddle.class);
        
        if (paddle != null && paddle.getPlayer() != playerPaddleLastHit && (lastHitPaddle == null || !lastHitPaddle.equals(paddle))) {
            hitCount++;
            
            if (hitCount == 10) {
                hitCount = 0;
                level++;
                TableTennisWorld world = getWorldOfType(TableTennisWorld.class);
                
                world.setLevel(world.getLevel() + 1);
                level = world.getLevel();
            }
            
            if ((paddle.getPlayer() && getY() < paddle.getY()) || (!paddle.getPlayer() && getY() > paddle.getY())) {
                reflectVertical((int)normalizeOffset(paddle), paddle.getPlayer());
            }
            
            lastHitPaddle = paddle;
            playerPaddleLastHit = paddle.getPlayer();
        } else if (paddle == null) {
            lastHitPaddle = null;
        }
    }
    
    private float normalizeOffset(Paddle paddle) {
        float offsetPower = 45;
        int paddleWidth = paddle.getWidth();
        float paddleHalfWidth = paddleWidth/2 + size/2;
        float offset = paddle.getX() - getX();
        
        if (!paddle.getPlayer()) {
            offset *= -1;
        }
        
        return offsetPower * offset / paddleHalfWidth;
    }
    
    public void reflectVertical() {
        setRotation(getRotation() * -1);
        playSound("collision", 60);
    }
    
    public void reflectVertical(int offset, boolean player) {
        setRotation((getRotation() + offset) * -1);
        playSound("collision", 60);
    }
    
    public void reflectHorizontal() {
        setRotation((getRotation() + 180) * -1);
        playSound("collision", 60);
    }
}