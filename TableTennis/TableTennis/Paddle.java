import greenfoot.*;

public class Paddle extends Actor
{
    private int width = 100;
    private int height = 25;
    private int moveSpeed = 5;
    
    private boolean playerPaddle = false;
    private Ball ballRef = null;
    
    private boolean simpleCPU = false;
    
    public Paddle(int width, int moveSpeed, boolean playerPaddle, boolean simple) {
        this.moveSpeed = moveSpeed;
        this.playerPaddle = playerPaddle;
        this.simpleCPU = simple;
        this.width = width;
        this.height = width/10;
                
        getImage().scale(width, height);
    }
    
    public boolean getPlayer() {
        return playerPaddle;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void act()
    {
        if (playerPaddle) {
            playerInput();
            worldCollision();
        } else if (simpleCPU) {
            simpleComputerInput();
        } else {
            advancedComputerInput();
            worldCollision();
        }
    }
    
    private void playerInput() {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            setLocation(getX() - moveSpeed, getY());
        } else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            setLocation(getX() + moveSpeed, getY());
        }
    }
    
    private void worldCollision() {
        if (getX() + width/2 > getWorld().getWidth()) {
            setLocation(getWorld().getWidth() - width/2, getY());
        } else if (getX() - width/2 < 0) {
            setLocation(width/2, getY());
        }
    }
    
    private void simpleComputerInput() {
        setLocation(getX() + moveSpeed, getY());
        
        if (isAtEdge() && getX() > getWorld().getWidth() + width/2) {
            width = Greenfoot.getRandomNumber(100)+50;
            getImage().scale(width, width/10);
            setLocation(-width/2, Greenfoot.getRandomNumber(getWorld().getHeight()/2) + getWorld().getHeight()/4);
        }
    }
    
    private void advancedComputerInput() {
        if (ballRef == null) {
            ballRef = getWorldOfType(TableTennisWorld.class).getBallRef();
        } else if (ballRef.wasPlayerPaddleLastHit()) {
            if (getX() < ballRef.getX()) {
                setLocation(getX() + moveSpeed, getY());
            } else if (getX() > ballRef.getX()) {
                setLocation(getX() - moveSpeed, getY());
            }
        } else {
            if (getX() < getWorld().getWidth()/2) {
                setLocation(getX() + moveSpeed, getY());
            } else if (getX() > getWorld().getWidth()/2) {
                setLocation(getX() - moveSpeed, getY());
            }
        }
    }
}