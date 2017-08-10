import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

/**
 * Help screen.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class HelpWorld extends World
{
    //  REFERENCES
    private Heading heading = null;
    private Description description = null;
    private Button button = null;
    
    /**
     * Constructor for objects of class HelpWorld.
     * 
     */
    public HelpWorld()
    {    
        // Create a new world with 800x500 cells with a cell size of 1x1 pixels.
        super(800, 500, 1);
        
        // Fill background with specified color
        GreenfootImage background = new GreenfootImage(getWidth(), getHeight());
        background.setColor(Utility.pale);
        background.fill();
        setBackground(background);

        // Add Description using specified text and font size
        description = new Description();
        addObject(description, getWidth() / 2, getHeight() / 2);
        
        // Add Heading using specified text, font size, and color
        heading = new Heading("HELP", 72, Utility.purple);
        addObject(heading, getWidth() / 2, description.getY() - 170);
        
        // Add Button using specified text, font size, text color, and background color
        button = new Button("play", 24, Utility.pale, Utility.purple);
        addObject(button, getWidth() / 2, description.getY() + 170);
    }
}