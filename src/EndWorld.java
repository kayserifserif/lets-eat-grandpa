import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

/**
 * End screen.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class EndWorld extends World
{
    //  REFERENCES
    private Heading heading = null;
    private Counter counter = null;
    private Button review = null;
    private Button playAgain = null;
    private Utility utility = null;
    
    /**
     * Constructor for objects of class EndWorld.
     * 
     * @param   score   The user's final score.
     * @param   utility Utility object.
     */
    public EndWorld(int score, Utility utility)
    {
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        this.utility = utility;
        
        // Fill background with specified color
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Utility.blue);
        background.fill();
        setBackground(background);
        
        // Add Counter using specified font size and color
        counter = new Counter(120, Utility.purple, score);
        addObject(counter, getWidth() / 2, getHeight() / 2);
        
        // Add Heading using specified text, font size, and color
        heading = new Heading("CONGRATULATIONS!", 64, Utility.purple);
        addObject(heading, getWidth() / 2, counter.getY() - 170);
        
        // Add Button using specified text, font size, text color, and background color
        review = new Button("review", 24, Utility.pale, Utility.purple);
        addObject(review, getWidth() / 3, counter.getY() + 150);
        
        // Add Button using specified text, font size, text color, and background color
        playAgain = new Button("play again", 24, Utility.pale, Utility.purple);
        addObject(playAgain, getWidth() - getWidth() / 3, counter.getY() + 150);
    }
    
    /**
     * Get Utility object.
     * 
     * @return  Utility object.
     */
    public Utility getUtility()
    {
        return utility;
    }
}