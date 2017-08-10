import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Gameplay screen.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class GameWorld extends World
{
    //  REFERENCES
    private Heading heading = null;
    private Counter counter = null;
    private Utility utility = null;
    private Sentence sentence = null;
    private Button hintButton = null;
    
    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld()
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        // Fill background with specified color
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Utility.purple);
        background.fill();
        setBackground(background);
        
        // Add Heading using specified text, font size, and color
        heading = new Heading("PLAY", 48, Utility.blue);
        addObject(heading, getWidth() / 2, getHeight() / 9);
        
        // Add Counter using specified font size, color, and score
        counter = new Counter(64, Utility.blue, 0);
        addObject(counter, getWidth() - getWidth()/4, getHeight()/3 - 15);
        
        // Create new Utility object, which populates the world with Word and Sentence objects.
        utility = new Utility();
        addObject(utility, 0, 0);
    }
    
    /**
     * Get Counter object;
     * 
     * @return  Counter object.
     */
    public Counter getCounter()
    {
        return counter;
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