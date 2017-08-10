import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Displays during review; allows the user to proceed to the next question.
 * 
 * @author  Katherine Yang
 * @version 1.0
 */
public class Arrow extends Actor
{
    //  REFERENCES
    private ReviewWorld rw = null;
    private Utility utility = null;
    private Sentence sentence = null;
    
    private String direction = "";
    
    private int[] xPoints = {0, 50, 0};
    private int[] yPoints = {0, 50, 100};
    
    GreenfootImage image = null;
    boolean mouseOver;
    boolean pressed;
    
    /**
     * Constructor for objects of class Arrow.
     */
    public Arrow()
    {
        image = new GreenfootImage(50, 100);
        
        image.setColor(Utility.blue);
        image.fillPolygon(xPoints, yPoints, 3);
        
        setImage(image);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // FROM BUSCH2207 AT http://www.greenfoot.org/topics/250
        // If mouse moves, set mouseOver to if it moved on this
        if(Greenfoot.mouseMoved(null))
        {
            mouseOver = Greenfoot.mouseMoved(this);
        }
        if(Greenfoot.mousePressed(this))
        {
            pressed = Greenfoot.mousePressed(this);
        }
        
        // If hovered over but not pressed, fill with a lighter color
        if(mouseOver && !pressed)
        {
            image.clear();
            image.setColor(Utility.blue.brighter());
            image.fillPolygon(xPoints, yPoints, 3);
            setImage(image);
        }
        // If pressed, fill with a darker color
        else if(pressed)
        {
            image.clear();
            image.setColor(Utility.blue.darker());
            image.fillPolygon(xPoints, yPoints, 3);
            setImage(image);
            pressed = Greenfoot.mouseClicked(this);
        }
        // If not hovered over and not pressed, fill with normal color
        else if(!mouseOver && !pressed)
        {
            image.clear();
            image.setColor(Utility.blue);
            image.fillPolygon(xPoints, yPoints, 3);
        }
        
        // If clicked, populate the world and change the sentence counter
        if (Greenfoot.mouseClicked(this))
        {
            rw = (ReviewWorld) getWorld();
            utility = rw.getUtility();
            utility.populate();
            sentence = utility.getSentence();
            sentence.next();
        }
    }    
}