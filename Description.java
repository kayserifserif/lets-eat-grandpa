import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Displays text in the Help screen that describes the game.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Description extends Actor
{
    private String text = "Find the grammatical error in each sentence! Your score will go up by 5 each time you get it right, and down by 5 if you get it wrong. There are 10 randomly-selected questions in one game. Good luck!";
    // Create a List that contains the words in the text String, split by spaces
    private List words = Arrays.asList(text.split(" "));
    // Final String that contains the description with "\n" at each line break
    private String description = "";
    
    //  IMAGE
    private int fontSize = 30;
    private Font font = new Font("Lato", Font.PLAIN, fontSize);
    
    /**
     * Constructor for objects of class Description.
     * 
     */
    public Description()
    {
        getImage().setFont(font);
        FontRenderContext frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        int width = 0;
        
        // Measure each word and insert "\n" at each line break
        for (int i = 0; i < words.size(); i++)
        {
            Rectangle2D bounds = font.getStringBounds(words.get(i) + "", frc);
            if((width += bounds.getWidth()) > 520)
            {
                description += "\n";
                width = (int)bounds.getWidth();
            }
            description += words.get(i) + " ";
        }
        // Create a List of Strings split by line breaks
        List strings = Arrays.asList(description.split("\n"));
        // Create a GreenfootImage 590 pixels wide and height according to the font size at 1.3 line height
        GreenfootImage image = new GreenfootImage(590, strings.size() * (int)Math.round(fontSize * 1.3));
        
        image.setColor(Utility.purple);
        image.setFont(font);
        
        // Draw each String at a line height of 1.2
        for (int i = 0; i < strings.size(); i++)
        {
            //image.drawString(strings.get(i) + "", 0, (i + 1) * (int)bounds.getHeight());
            image.drawString(strings.get(i) + "", 0, (int)Math.round((i + 1) * fontSize * 1.2));
        }
        
        setImage(image);
    }
}