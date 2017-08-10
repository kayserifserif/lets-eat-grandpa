import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

/**
 * Keeps track of and displays the score.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Counter extends Actor
{
    //  REFERENCES
    private GameWorld gw = null;
    private Sentence sentence = null;
    private Utility utility = null;
    
    //  DATA
    public int score = 0;
    
    //  IMAGE
    private String label = "SCORE";
    private int fontSize;
    private Color textColor = null;
    private GreenfootImage image = null;
    
    private Font labelFont = null;
    private FontRenderContext labelFRC = null;
    private Rectangle2D labelBounds = null;
    
    private Font scoreFont = null;
    private FontRenderContext scoreFRC = null;
    private Rectangle2D scoreBounds = null;
    
    /**
     * Constructor for objects of class Counter.
     * 
     * @param   fontSize    The size of the font.
     * @param   textColor   The color of the text.
     * @param   score       The score to be displayed at the end screen.
     * 
     */
    public Counter(int fontSize, Color textColor, int score)
    {
        this.score = score;
        this.textColor = textColor;
        this.fontSize = fontSize;
        
        labelFont = new Font("Oswald", Font.PLAIN, (int)Math.round(fontSize * 0.4));
        scoreFont = new Font("Lato", Font.BOLD, fontSize);
        
        getImage().setFont(labelFont);
        labelFRC = getImage().getAwtImage().createGraphics().getFontRenderContext();
        labelBounds = labelFont.getStringBounds("SCORE", labelFRC);
        
        getImage().setFont(scoreFont);
        scoreFRC = getImage().getAwtImage().createGraphics().getFontRenderContext();
        
        updateImage();
    }
    
    /**
     * Increases the score, populates the word, and increments the sentence counter.
     */
    public void increase()
    {
        // Increase score by 5
        score += 5;
        updateImage();
        
        gw = (GameWorld) getWorld();
        utility = gw.getUtility();
        utility.populate();
        
        sentence = utility.getSentence();
        sentence.next();
    }
    
    /**
     * Decreases the score.
     */
    public void decrease()
    {
        // Decrease score by 5
        score -= 5;
        updateImage();
    }
    
    /**
     * Update image based on the score.
     */
    public void updateImage()
    {
        // Calculates and displays the score with two digits
        scoreBounds = scoreFont.getStringBounds(String.format("%02d", score), scoreFRC);
        
        // Create GreenfootImage with width based on width of score or label, depending on which one is wider
        if ((int)scoreBounds.getWidth() > (int)labelBounds.getWidth())
        {
            image = new GreenfootImage((int)scoreBounds.getWidth() + 2,
                (int)labelBounds.getHeight() + (int)scoreBounds.getHeight());
        }
        else if ((int)labelBounds.getWidth() > (int)scoreBounds.getWidth())
        {
            image = new GreenfootImage((int)labelBounds.getWidth() + 2,
                (int)labelBounds.getHeight() + (int)scoreBounds.getHeight());
        }
        
        image.setColor(textColor);
        
        image.setFont(labelFont);
        image.drawString("SCORE", 0, Math.round((int)labelBounds.getHeight()));
        
        image.setFont(scoreFont);
        image.drawString(String.format("%02d", score), 0, (int)labelBounds.getHeight()/3 + (int)scoreBounds.getHeight());
        
        setImage(image);
    }
    
    /**
     * Get score.
     * 
     * @return  The score.
     */
    public int getScore()
    {
        return score;
    }
}