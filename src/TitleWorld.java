import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

/**
 * Title screen.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class TitleWorld extends World
{
    //  REFERENCES
    private Heading heading = null;
    private Button button = null;
    
    /**
     * Constructor for objects of class TitleWorld.
     * 
     */
    public TitleWorld()
    {
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        // Fill background with specified color
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Utility.blue);
        background.fill();
        setBackground(background);
        
        // Add Heading using specified text, font size, and color
        heading = new Heading("LET'S EAT GRANDPA!", 64, Utility.purple);
        addObject(heading, getWidth() / 2, getHeight() / 2 - 60);
        
        // Add Button using specified text, font size, text color, and background color
        button = new Button("start", 30, Utility.pale, Utility.purple);
        addObject(button, getWidth() / 2, getHeight() / 2 + 60);
    }
}