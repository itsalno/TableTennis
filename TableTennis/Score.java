import greenfoot.*;

public class Score extends Actor
{
    private int width = 0;
    
    public Score(int width) {
        this.width = width;
        draw();
    }
    
    public void updateScore() {
        draw();
    }
    
    private void draw() {
        GreenfootImage newImage = new GreenfootImage(width, 100);
        newImage.setFont(new Font(true, false, 24));
        newImage.setColor(new Color(255, 255, 255));
        newImage.drawString(Integer.toString(ScoreManager.getComputerScore()), 20, 35);
        newImage.drawString(Integer.toString(ScoreManager.getPlayerScore()), 20, 85);
        
        setImage(newImage);
    }
}