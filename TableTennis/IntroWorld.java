import greenfoot.*;

public class IntroWorld extends World
{
    public IntroWorld()
    {    
        super(400, 600, 1); 
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new TableTennisWorld());
        }
    }
}
