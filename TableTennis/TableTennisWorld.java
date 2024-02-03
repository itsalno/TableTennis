import greenfoot.*;

public class TableTennisWorld extends World
{
    private int playerScore = 0;
    private int computerScore = 0;
    private int level = 1;
    
    private Level levelCounter;
    private Ball ballRef;
    private Score scoreCounter;
    
    private GreenfootSound backgroundTrack;
    private boolean musicInit = false;
    
    public TableTennisWorld()
    {    
        super(400, 600, 1, false); 
        prepare();
    }
    
    public void prepare() {
        ballRef = new Ball();
        addObject(ballRef, getWidth()/2, getHeight()/2);
        ballRef.resetBall();
        
        addObject(new Paddle(Greenfoot.getRandomNumber(100)+50, 5, true, false), getWidth()/2, getHeight() - 50);
        
        addObject(new Paddle(Greenfoot.getRandomNumber(100)+50, 2, false, false), getWidth()/2, 50);
        addObject(new Paddle(Greenfoot.getRandomNumber(100)+50, 2, false, true), getWidth()/2, Greenfoot.getRandomNumber(getHeight()/2) + getHeight()/4);
        
        levelCounter = new Level("Game Level: 1");
        addObject(levelCounter, getWidth() - 60, 45);
        
        scoreCounter = new Score(getWidth());
        addObject(scoreCounter, getWidth()/2, getHeight()/2);
        
        setPaintOrder(Ball.class);
    }
    
    public void act() {
        if (!musicInit) {
            backgroundTrack = new GreenfootSound("PP_Fight_or_Flight_FULL_Loop.wav");
            backgroundTrack.setVolume(20);
            backgroundTrack.playLoop();
            
            musicInit = true;
        } 
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
        
        levelCounter.updateText("Game Level: " + Integer.toString(level));
    }
    
    public Ball getBallRef() {
        return ballRef;
    }
    
    public void stopMusic() {
        backgroundTrack.stop();
    }
    
    public void updateScore() {
        scoreCounter.updateScore();
    }
}