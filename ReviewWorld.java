import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

import java.util.ArrayList;

/**
 * Review screen.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class ReviewWorld extends World
{
    //  REFERENCES
    private Heading heading = null;
    private Utility utility = null;
    private Arrow arrow = null;
    
    /**
     * Constructor for objects of class ReviewWorld.
     * 
     * @param   utility Utility object.
     */
    public ReviewWorld(Utility utility)
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        // Fill background with specified color
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Utility.pale);
        background.fill();
        setBackground(background);
        
        // Add Heading with specified text, font size, and color
        heading = new Heading("REVIEW", 48, Utility.purple);
        addObject(heading, getWidth() / 2, getHeight() / 9);
        
        this.utility = utility;
        addObject(utility, 0, 0);
        
        // Add Arrow
        arrow = new Arrow();
        addObject(arrow, getWidth() - 75, getHeight() / 2);
    }
    
    /**
     * Return Utility object.
     * 
     * @return  Utility object.
     */
    public Utility getUtility()
    {
        return utility;
    }
}