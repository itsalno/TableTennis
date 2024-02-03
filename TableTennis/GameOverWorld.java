import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverWorld extends World
{

    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld(boolean playerWon)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1); 
        
        Text textObject = new Text();
        GreenfootImage newImage = new GreenfootImage(getWidth(), 100);
        
        String text = (playerWon) ? "You won!" : "You lost!";
        newImage.setColor(new Color(255,255,255,230));
        newImage.fill();
        
        newImage.setColor(new Color(0,0,0));
        newImage.setFont(new Font(true, false, 28));
        newImage.drawString(text, 140, 40);
        
        newImage.setFont(new Font(false, false, 18));
        newImage.drawString("Press 'enter' to play again.", 95, 75);
        textObject.setImage(newImage);
        
        addObject(textObject, getWidth()/2, getHeight()/2);
        
        GreenfootSound endGameSFX = (playerWon) ? new GreenfootSound("Event 3.mp3") : new GreenfootSound("Event 6.mp3");
        endGameSFX.setVolume(20);
        endGameSFX.play();
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new TableTennisWorld());
        }
    }
}
