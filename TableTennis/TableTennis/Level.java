import greenfoot.*;

public class Level extends Actor
{    
    public Level(String text) {        
        draw(text);
    }
    
    public void updateText(String text) {        
        draw(text);
    }
    
    private void draw(String text) {
        GreenfootImage newImage = new GreenfootImage(125, 50);
        newImage.setColor(new Color(255, 255, 255));
        newImage.setFont(new Font(true, false, 12));
        newImage.drawString(text, 10, 10);
        
        setImage(newImage);
    }
}